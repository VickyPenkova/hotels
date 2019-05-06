package com.java.nbu.hotels.controller;

import com.java.nbu.hotels.entities.UserEntity;
import com.java.nbu.hotels.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
   @Autowired
   private UserService userService;

   @RequestMapping(method = RequestMethod.GET)
   public String index() {
      return "Hello! Welcome to your profile!";
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
