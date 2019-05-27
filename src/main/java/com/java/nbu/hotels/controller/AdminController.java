package com.java.nbu.hotels.controller;

import com.java.nbu.hotels.entities.BookingEntity;
import com.java.nbu.hotels.entities.RoomEntity;
import com.java.nbu.hotels.entities.UserEntity;
import com.java.nbu.hotels.service.BookingService;
import com.java.nbu.hotels.service.EmailSenderService;
import com.java.nbu.hotels.service.RoomService;
import com.java.nbu.hotels.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
   @Autowired
   private BookingService bookingService;

   @Autowired
   private EmailSenderService emailSenderService;

   @Autowired
   private RoomService roomService;

   @RequestMapping(value = {"/adminPage"}, method = RequestMethod.GET)
   public ModelAndView adminPage() {
      List<BookingEntity> allBookings = bookingService.getAllBookings();

      ModelAndView model = new ModelAndView();
      model.setViewName("admin");
      model.addObject("allBookings", allBookings);
      return model;
   }

   @RequestMapping(value = {"/confirm/{id}"}, method = RequestMethod.GET)
   public ModelAndView confirmBooking(@PathVariable String id ){
      BookingEntity booking = bookingService.getBookingById(id);
      booking.setStatus("confirmed");
      bookingService.updateBookingStatus(booking);
      ModelAndView m = new ModelAndView();

      // Send mail
      SimpleMailMessage mailMessage = new SimpleMailMessage();
      mailMessage.setTo(booking.getUser().getEmail());
      mailMessage.setSubject("Booking confirmed!");
      mailMessage.setFrom("admin@H.com");
      mailMessage.setText("Congratulations! Your booking has been confirmed! Checkin date: " + booking.getStartDate() +
            " Checkout date: " + booking.getEndDate() + "Enjoy your stay!");

      emailSenderService.sendEmail(mailMessage);

      // TODO: Sveti to make page for confirmed booking for admin
      m.setViewName("successfulBooking");
      return m;
   }

   @RequestMapping(value = {"/decline/{id}"}, method = RequestMethod.GET)
   public ModelAndView declineBooking(@PathVariable String id ){
      BookingEntity booking = bookingService.getBookingById(id);
      booking.setStatus("declined");
      bookingService.updateBookingStatus(booking);
      ModelAndView m = new ModelAndView();

      SimpleMailMessage mailMessage = new SimpleMailMessage();
      mailMessage.setTo(booking.getUser().getEmail());
      mailMessage.setSubject("Booking confirmed!");
      mailMessage.setFrom("vicky.penkova@gmail.com");
      mailMessage.setText("Your booking has been declined! Please contact the admin for more information!");
      emailSenderService.sendEmail(mailMessage);
      // TODO: Sveti to make page for declined booking for admin
      m.setViewName("successfulBooking");
      return m;
   }

   @RequestMapping(value = {"/addRoom"}, method = RequestMethod.GET)
   public ModelAndView addRoomForm(ModelAndView m, RoomEntity newRoom){
      m.setViewName("addroom");
      m.addObject("newRoom", newRoom);

      return m;
   }

   @RequestMapping(value = {"/addRoom"}, method = RequestMethod.POST)
   public ModelAndView addRoom(ModelAndView m, RoomEntity newRoom){
      roomService.addRoom(newRoom);

      m.setViewName("roomAdded");

      return m;
   }
}
