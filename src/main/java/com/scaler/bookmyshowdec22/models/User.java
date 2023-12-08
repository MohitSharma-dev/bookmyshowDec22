package com.scaler.bookmyshowdec22.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class User extends BaseModel{
    private String name;
    private String email;
    // 1 : M
    // 1 : 1
    @OneToMany
    private List<Booking> bookings;
}
