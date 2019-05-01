package com.java.nbu.hotels.controller;

import com.java.nbu.hotels.entities.RoomEntity;
import com.java.nbu.hotels.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@EnableAutoConfiguration
public class RoomController {
   @Autowired(required = false)
   private RoomService roomService;

   @RequestMapping("/rooms/all")
   public List<RoomEntity> getRooms() {
      return roomService.getRooms();
   }
}
