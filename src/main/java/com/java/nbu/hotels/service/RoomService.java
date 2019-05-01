package com.java.nbu.hotels.service;

import com.java.nbu.hotels.entities.RoomEntity;
import com.java.nbu.hotels.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {

    @Autowired private RoomRepository roomRepository;

    public RoomEntity getRooms(){
        return roomRepository.findRoomEntityById(1);
    }
    public void addRoom(RoomEntity roomEntity) {
        roomRepository.save(roomEntity);
    }

}
