package com.scaler.bookmyshowdec22.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat extends BaseModel{
    private String seatNumber;
    // 1 : 1
    // M : 1
    @ManyToOne
    private SeatType seatType;
    private int rowVal;
    private int colVal;
}
