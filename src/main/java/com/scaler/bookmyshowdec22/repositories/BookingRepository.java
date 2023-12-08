package com.scaler.bookmyshowdec22.repositories;

import com.scaler.bookmyshowdec22.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
