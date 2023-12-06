package com.scaler.bookmyshowdec22.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel{
    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus;
    // B : S
    // 1 : M
    // M : 1
    @ManyToMany
    private List<ShowSeat> showSeats;
    @ManyToOne
    private User user;
    private Date bookedAt;
    @ManyToOne
    private Show show;
    private int amount;
    // 1 : M
    // 1 : 1
    @OneToMany
    private List<Payment> payments;
}
