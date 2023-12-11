package com.scaler.bookmyshowdec22.services;

import com.scaler.bookmyshowdec22.models.Show;
import com.scaler.bookmyshowdec22.models.ShowSeat;
import com.scaler.bookmyshowdec22.models.ShowSeatType;
import com.scaler.bookmyshowdec22.repositories.ShowSeatTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceCalculator {
    private ShowSeatTypeRepository showSeatTypeRepository;
    @Autowired
    PriceCalculator(ShowSeatTypeRepository showSeatTypeRepository){
        this.showSeatTypeRepository = showSeatTypeRepository;
    }
    public int calculatePrice(List<ShowSeat> showSeats,
                              Show show) {
        // 1. Find out the type of seats in the given show
        // 2. get seatTypes for all the selected seats
        // 3. calculate the amount

        List<ShowSeatType> showSeatTypes = showSeatTypeRepository.findAllByShow(show);
        int amount = 0;
        for(ShowSeat showSeat : showSeats){
            for(ShowSeatType showSeatType : showSeatTypes){
                if(showSeat.getSeat().getSeatType().equals(showSeatType.getSeatType())) {
                    amount += showSeatType.getPrice();
                }
            }
        }

        return amount;
    }

}

// Animal23 A1
// Animal23 A2
// Animal23 A3
// Animal23 B1

// Animal23 GOLD, SILVER AND PLATINUM
