package com.java.nbu.hotels.controller;


import com.java.nbu.hotels.entities.RoomEntity;
import com.java.nbu.hotels.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("roomController")
public class RoomController {

   @Autowired(required = false)
   private RoomService roomService;

   @RequestMapping("/rooms")
   public List<RoomEntity> getRooms() {
      return roomService.getRooms();
   }

   @RequestMapping(method = RequestMethod.POST, value = "/rooms")
   public void addCourse(@RequestBody RoomEntity roomEntity) {
      roomService.addRoom(roomEntity);
       System.out.println(roomEntity);
   }
    @RequestMapping(method = RequestMethod.PUT, value = "/rooms")
    public void updateCourse(@RequestBody RoomEntity roomEntity) {
        roomService.updateRoom(roomEntity);
    }

   @RequestMapping(method = RequestMethod.DELETE, value = "/rooms/{id}")
   public void deleteCourse(@PathVariable("id") int id) {
      roomService.deleteRoom(id);
   }
}
