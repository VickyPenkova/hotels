package com.java.nbu.hotels;

import com.java.nbu.hotels.controller.RoomController;
import com.java.nbu.hotels.repository.RoomRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class HotelsApplication {
   public static void main(String[] args) {
      SpringApplication.run(HotelsApplication.class, args);
   }

}

