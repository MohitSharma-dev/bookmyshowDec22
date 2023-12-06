package com.scaler.bookmyshowdec22.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat extends BaseModel{
    private String seatNumber;

    private SeatType seatType;
    private int rowVal;
    private int colVal;
}
