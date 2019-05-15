package com.java.nbu.hotels.repository;

import com.java.nbu.hotels.entities.BookingEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookingRepository extends CrudRepository<BookingEntity, Integer> {
   List<BookingEntity> findByUserId(int userId);
   BookingEntity findBookingEntityByIdBooking(int bookingId);
   List<BookingEntity> findAllByStatus(String status);
}
