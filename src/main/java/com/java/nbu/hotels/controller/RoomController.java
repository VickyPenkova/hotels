package com.java.nbu.hotels.controller;


import com.java.nbu.hotels.entities.RoomEntity;
import com.java.nbu.hotels.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoomController {

   @Autowired
   private RoomService roomService;

   @RequestMapping("/rooms")
   public RoomEntity getRooms() {
      return roomService.getRooms();
   }
   @RequestMapping(method = RequestMethod.POST, value = "/rooms")
   public void addCourse(@RequestBody RoomEntity roomEntity) {
      roomService.addRoom(roomEntity);
   }

}
