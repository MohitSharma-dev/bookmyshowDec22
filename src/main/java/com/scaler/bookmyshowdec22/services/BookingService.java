package com.scaler.bookmyshowdec22.services;

import com.scaler.bookmyshowdec22.models.*;
import com.scaler.bookmyshowdec22.repositories.BookingRepository;
import com.scaler.bookmyshowdec22.repositories.ShowRepository;
import com.scaler.bookmyshowdec22.repositories.ShowSeatRepository;
import com.scaler.bookmyshowdec22.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.awt.print.Book;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private UserRepository userRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private BookingRepository bookingRepository;
    private PriceCalculator priceCalculator;
    @Autowired
    BookingService(UserRepository userRepository,
                   ShowRepository showRepository,
                   ShowSeatRepository showSeatRepository,
                   BookingRepository bookingRepository,
                   PriceCalculator priceCalculator){
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.bookingRepository = bookingRepository;
        this.priceCalculator = priceCalculator;
    }
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookMovie(
            Long userId,
            List<Long> showSeatIds,
            Long showId
    ) {
        // 1. Get the user by userId
        Optional<User> userOptional= userRepository.findById(userId);

        if(userOptional.isEmpty()){
            throw new RuntimeException("No such user found!");
        }
        User bookedBy = userOptional.get();
        // 2. get the show with showId
        Optional<Show> showOptional = showRepository.findById(showId);
        if(showOptional.isEmpty()){
            throw new RuntimeException("No show like that!");
        }
        Show bookedShow = showOptional.get();
        // ------ Take lock --------- (start transaction)
        // 3. get the showSeat using seatIds

        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);
        // 4. check if all the seats are available
        for(ShowSeat showSeat : showSeats){
            if(!(showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE) ||
                    (showSeat.getShowSeatStatus().equals(ShowSeatStatus.BLOCKED) &&
                            Duration.between(showSeat.getLockedAt().toInstant() , new Date().toInstant()).toMinutes() > 15))){
                // 5. if no , throw error
                throw new RuntimeException("Selected seats are not available!");
            }
        }

        List<ShowSeat> savedShowSeats = new ArrayList<>();
        // 6. if yes, mark all the selected seats as BLOCKED
        for(ShowSeat showSeat : showSeats){
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
            showSeat.setLockedAt(new Date());
            // 7. Save it in the database
            savedShowSeats.add(showSeatRepository.save(showSeat));
        }

        // ------- release the lock : end transaction -----
        // 8. Create the corresponding Booking object
        Booking booking  = new Booking();
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setShowSeats(savedShowSeats);
        booking.setUser(bookedBy);
        booking.setBookedAt(new Date());
        booking.setShow(bookedShow);
        booking.setAmount(priceCalculator.calculatePrice(savedShowSeats, bookedShow));
        booking.setPayments(new ArrayList<>());
        // 9. save the booking details in the database
        booking = bookingRepository.save(booking);
        // 10. return  the booking object
        return booking;
    }
}
