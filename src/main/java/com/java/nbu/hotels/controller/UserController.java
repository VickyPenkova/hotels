package com.java.nbu.hotels.controller;

import com.java.nbu.hotels.entities.BookingEntity;
import com.java.nbu.hotels.entities.UserEntity;
import com.java.nbu.hotels.service.BookingService;
import com.java.nbu.hotels.service.RoomService;
import com.java.nbu.hotels.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
   @Autowired
   private UserService userService;

   @Autowired
   private BookingService bookingService;

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
      ModelAndView mav = new ModelAndView();
      mav.addObject("bookings", bookings);
      mav.setViewName("dashboard");

      return mav;
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
