package com.scaler.bookmyshowdec22.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Payment extends BaseModel{
    private String refNo;
    private int amount;
//    private Booking booking;
    private PaymentStatus paymentStatus;
    private PaymentProvider paymentProvider;
}
