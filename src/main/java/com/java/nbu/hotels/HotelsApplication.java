package com.java.nbu.hotels;

import com.java.nbu.hotels.controller.RoomController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class HotelsApplication {
   public static void main(String[] args) {
      SpringApplication.run(RoomController.class, args);
   }

}
