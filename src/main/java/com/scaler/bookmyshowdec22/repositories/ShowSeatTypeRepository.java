package com.scaler.bookmyshowdec22.repositories;

import com.scaler.bookmyshowdec22.models.Show;
import com.scaler.bookmyshowdec22.models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType, Long> {

    List<ShowSeatType> findAllByShow(Show show);
}
