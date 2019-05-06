package com.java.nbu.hotels.service;

import com.java.nbu.hotels.entities.BookingEntity;
import com.java.nbu.hotels.entities.RoomEntity;
import com.java.nbu.hotels.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {
   private final BookingRepository bookingRepository;

   @Autowired
   public BookingService(BookingRepository bookingRepository) {
      this.bookingRepository = bookingRepository;
   }

   public List<BookingEntity> getBookingsOfUser(int userId){
      List<BookingEntity> bookings = new ArrayList();
      bookingRepository.findByUserId(userId).forEach(booking -> bookings.add(booking));
      return bookings;
   }


}
