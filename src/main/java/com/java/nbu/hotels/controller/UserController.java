package com.java.nbu.hotels.controller;

import com.java.nbu.hotels.entities.BookingEntity;
import com.java.nbu.hotels.entities.RoomEntity;
import com.java.nbu.hotels.entities.UserEntity;
import com.java.nbu.hotels.service.BookingService;
import com.java.nbu.hotels.service.RoomService;
import com.java.nbu.hotels.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
   @Autowired
   private UserService userService;

   @Autowired
   private BookingService bookingService;

   @Autowired
   private RoomService roomService;

   @RequestMapping("/dashboard")
   public ModelAndView dashboard() {
      int userId;
      Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      if (principal instanceof UserDetails) {
         userId = ((UserEntity)principal).getId();
      } else {
         userId = 0;
      }

      List<BookingEntity> bookings = bookingService.getBookingsOfUser(userId);
      System.out.println("Is booking empty:" + bookings.isEmpty());
      try {
         if(bookings != null){
            for (BookingEntity b:
                  bookings) {
               if (b.getStatus().equals("noroom")) {
                  bookingService.deleteBookingWithStatusNoroom(b);
                  bookings.remove(b);
               }
            }
         }
      } catch(Exception e){
         e.getMessage();
      }
      ModelAndView mav = new ModelAndView();
      mav.addObject("bookings", bookings);
      mav.setViewName("dashboard");

      return mav;
   }

   @RequestMapping(value = "/booking",method = RequestMethod.GET)
   public ModelAndView pickDate(){
      BookingEntity newBooking = new BookingEntity();
      ModelAndView mod = new ModelAndView();
      mod.setViewName("booking");
      mod.addObject("newBooking", newBooking);

      return mod;
   }

   @RequestMapping(value="/createBooking",method = RequestMethod.POST)
   public ModelAndView pickDate(@ModelAttribute("newBooking") BookingEntity bookingEntity){
      int userId;
      Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      if (principal instanceof UserDetails) {
         userId = ((UserEntity)principal).getId();
      } else {
         userId = 0;
      }
      UserEntity user = userService.findById(userId);
      bookingEntity.setUser(user);
      bookingEntity.setStatus("noroom");

      //persist
      bookingService.createBooking(bookingEntity);
      ModelAndView bookingStepTwoModel = new ModelAndView();
      bookingStepTwoModel.addObject(bookingService.getStartAndEndDate(bookingEntity.getIdBooking()));

      List<RoomEntity> allRooms = roomService.getRooms();
      List<RoomEntity> availableRooms = new ArrayList<>();

      Date startDateAsDate=Date.valueOf(bookingEntity.getStartDate());//converting string into sql date
      Date endDateAsDate=Date.valueOf(bookingEntity.getEndDate());//converting string into sql date
      boolean isAvailable = true;
      for (RoomEntity r:
           allRooms) {
         // Case 1: there are no booking for the certain room ->
         // rooms are available for reservations for any period
         if(r.getBooking() == null){
            availableRooms.add(r);
         } else {
            // We have booked reservation for the certain room
            // As we need to go through all of them
            for (BookingEntity booking:
                 r.getBooking()) {
               Date startDateAsDateInBooking = Date.valueOf(booking.getStartDate());
               Date endDateAsDateInBooking = Date.valueOf(booking.getEndDate());
               // End date of current < booked.startDate
               // OR
               // Start date of current > booked.endDate
               // If non of the above are satisfied
               // the room is occupied
               if(!(endDateAsDate.compareTo(startDateAsDateInBooking) < 0
               || startDateAsDate.compareTo(endDateAsDateInBooking)>0)){
                  isAvailable=false;
                  break;
               }
            }
            if(isAvailable){
               availableRooms.add(r);
            }
         }
      }

      // Check if there are any available rooms
      //
      if(availableRooms.size()==0){
         bookingService.deleteBooking(bookingEntity);
         bookingStepTwoModel.setViewName("noAvailableRooms");
      }else {
         bookingStepTwoModel.setViewName("bookingStepTwo");
         bookingStepTwoModel.addObject("availableRooms",availableRooms);
         //bookingStepTwoModel.addObject("bookingEntity", bookingEntity);
      }

      bookingStepTwoModel.addObject("dropdownSelectedValue", "");
      return bookingStepTwoModel;
   }

   @RequestMapping(value="/confirm/{id}/{bookingid}",method = RequestMethod.GET)
   public ModelAndView confirmBooking(@PathVariable String id, @PathVariable String bookingid){
      // set the booking status to "pending", currently should be "noroom"
      BookingEntity bookingEntity = bookingService.getBookingById(bookingid);

      bookingService.linkRoomToBooking(id, bookingid);

      System.out.println("Rooms linked by booking:" + bookingEntity.getRooms());

      RoomEntity r = roomService.findRoomById(id);

      System.out.println("Booking linked by room" + r.getBooking());

      bookingEntity.setStatus("pending");
      bookingService.updateBookingStatus(bookingEntity);

      ModelAndView model = new ModelAndView();
      model.setViewName("successfulBooking");
      return model;
   }



   @PreAuthorize("hasAnyRole('USER')")
   @RequestMapping("/hello")
   public String sayHello(){
      return "Hello User!";
   }

   @RequestMapping("/users")
   public List<UserEntity> getUsers(){
      return userService.getUsers();
   }

   @RequestMapping(method = RequestMethod.POST, value = "/users")
   public void addUser(@RequestBody UserEntity user) {
      userService.saveUser(user);
      System.out.println(user);
   }
   @RequestMapping(method = RequestMethod.PUT, value = "/users")
   public void updateUser(@RequestBody UserEntity user) {
      userService.updateUserInfo(user);
   }

   @RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
   public void deleteUser(@PathVariable("id") int id) {
      userService.deleteUser(id);
   }
}
