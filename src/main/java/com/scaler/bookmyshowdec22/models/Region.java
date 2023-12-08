package com.scaler.bookmyshowdec22.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Region extends BaseModel{
    private String name;
    // 1 : M
    // 1 : 1
    @OneToMany
    private List<Theatre> theatres;
}
