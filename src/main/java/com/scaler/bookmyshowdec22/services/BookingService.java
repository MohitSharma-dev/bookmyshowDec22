package com.scaler.bookmyshowdec22.services;

import com.scaler.bookmyshowdec22.models.Booking;
import com.scaler.bookmyshowdec22.models.User;
import com.scaler.bookmyshowdec22.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private UserRepository userRepository;
    @Autowired
    BookingService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookMovie(
            Long userId,
            List<Long> seatIds,
            Long showId
    ) {
        // 1. Get the user by userId
        Optional<User> userOptional= userRepository.findById(userId);

        // 2. get the show with showId
        // ------ Take lock --------- (start transaction)
        // 3. get the showSeat using seatIds
        // 4. check if all the seats are available
        // 5. if no , throw error
        // 6. if yes, mark all the selected seats as BLOCKED
        // 7. Save it in the database
        // ------- release the lock : end transaction -----
        // 8. Create the corresponding Booking object
        // 9. save the booking details in the database
        // 10. return  the booking object


        return null;
    }
}
