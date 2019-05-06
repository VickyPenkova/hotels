package com.java.nbu.hotels.service;

import com.java.nbu.hotels.entities.BookingEntity;
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

   public void createBooking(BookingEntity newBooking){
      bookingRepository.save(newBooking);
   }

   public List<String> getStartAndEndDate(int bookingId){
      BookingEntity booking = bookingRepository.findBookingEntityByIdBooking(bookingId);
      String startDate =booking.getStartDate();
      String endDate = booking.getEndDate();
      List<String> listDates= new ArrayList<>();
      listDates.add(startDate);
      listDates.add(endDate);

      return listDates;
   }

   public void deleteBooking(BookingEntity bookingToDelete){
      bookingRepository.delete(bookingToDelete);
   }

}
