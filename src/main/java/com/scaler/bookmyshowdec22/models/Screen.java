package com.scaler.bookmyshowdec22.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Screen extends BaseModel{
    private String name;
    // 1 : M
    // 1 : 1
    @OneToMany
    private List<Seat> seats;
    @Enumerated(EnumType.ORDINAL)
    @ElementCollection // Mapping table for enums screen_features
    private List<Feature> features;
}


// not the right way
// s1 2d,3d,dolby,recliner

// right way
// s1 2d
// s1 3d
// s1 dolby
// s1 recliner