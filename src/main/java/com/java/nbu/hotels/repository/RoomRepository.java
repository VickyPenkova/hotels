package com.java.nbu.hotels.repository;

import com.java.nbu.hotels.entities.RoomEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoomRepository extends CrudRepository<RoomEntity, Integer> {

    RoomEntity findRoomEntityById(int id);
    List<RoomRepository> findByType(String type);
    List<RoomRepository> findByTotalRoomsCnt(int totalRoomsCnt);
}
