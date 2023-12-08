package com.scaler.bookmyshowdec22.repositories;

import com.scaler.bookmyshowdec22.models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {
    @Override
    List<ShowSeat> findAllById(Iterable<Long> longs);
}
