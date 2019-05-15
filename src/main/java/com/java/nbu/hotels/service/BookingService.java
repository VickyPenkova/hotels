package com.java.nbu.hotels.service;

import com.java.nbu.hotels.entities.BookingEntity;
import com.java.nbu.hotels.entities.RoomEntity;
import com.java.nbu.hotels.repository.BookingRepository;
import com.java.nbu.hotels.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {
   private final BookingRepository bookingRepository;

   private final RoomRepository roomRepository;

   @Autowired
   public BookingService(BookingRepository bookingRepository, RoomRepository roomRepository) {
      this.bookingRepository = bookingRepository;
      this.roomRepository = roomRepository;
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

   public BookingEntity getBookingById(String id){
      int bookingid = Integer.parseInt(id);
      return bookingRepository.findBookingEntityByIdBooking(bookingid);
   }

   public void deleteBookingWithStatusNoroom(BookingEntity booking){
      bookingRepository.delete(booking);
   }

   public void updateBookingStatus(BookingEntity booking){
      bookingRepository.save(booking);
   }

   public void linkRoomToBooking(String roomid,String bookingid){
      int bId = Integer.parseInt(bookingid);
      int rId = Integer.parseInt(roomid);
      BookingEntity b = bookingRepository.findBookingEntityByIdBooking(bId);
      RoomEntity r = roomRepository.findRoomEntityByRoomid(rId);
      b.getRooms().add(r);
      bookingRepository.save(b);
      r.getBooking().add(b);
      roomRepository.save(r);
   }

   public List<BookingEntity> getAllBookings(){
      return bookingRepository.findAllByStatus("pending");
   }
}
