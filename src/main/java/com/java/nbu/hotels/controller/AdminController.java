package com.java.nbu.hotels.controller;

import com.java.nbu.hotels.entities.BookingEntity;
import com.java.nbu.hotels.entities.RoomEntity;
import com.java.nbu.hotels.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
   @Autowired
   private BookingService bookingService;

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

      // TODO: Sveti to make page for declined booking for admin
      m.setViewName("successfulBooking");
      return m;
   }
}
