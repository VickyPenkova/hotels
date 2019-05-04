package com.java.nbu.hotels.repository;

import com.java.nbu.hotels.entities.RoomEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends CrudRepository<RoomEntity, Integer> {

    RoomEntity findRoomEntityById(int id);
    List<RoomRepository> findByType(String type);
    List<RoomRepository> findByTotalRoomsCnt(int totalRoomsCnt);
}
