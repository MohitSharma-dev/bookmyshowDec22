package com.scaler.bookmyshowdec22.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookMovieRequestDTO {
    private List<Long> showSeatIds;
    private Long userId;
    private Long showId;
 }
