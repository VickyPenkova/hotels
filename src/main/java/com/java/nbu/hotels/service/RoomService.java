package com.java.nbu.hotels.service;

import com.java.nbu.hotels.entities.RoomEntity;
import com.java.nbu.hotels.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<RoomEntity> getRooms(){
        List<RoomEntity> roomEntities = new ArrayList();
        roomRepository.findAll().forEach(roomEntity -> roomEntities.add(roomEntity));
        return roomEntities;
    }

    public void addRoom(RoomEntity roomEntity) {
        roomRepository.save(roomEntity);
    }

    public void updateRoom(RoomEntity roomEntity){
        roomRepository.save(roomEntity);
    }

    public void deleteRoom(int id){
        RoomEntity roomToDelete = roomRepository.findRoomEntityById(id);
        roomRepository.delete(roomToDelete);
    }
}
