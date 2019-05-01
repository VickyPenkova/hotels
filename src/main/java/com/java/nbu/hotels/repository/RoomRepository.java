package com.java.nbu.hotels.repository;

import com.java.nbu.hotels.entities.RoomEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
@Component("roomRepo")
@Repository
public interface RoomRepository extends CrudRepository<RoomEntity, Long> {
   List<RoomEntity> findById(long id);

   List<RoomEntity> findByType(String type);

   List<RoomEntity> findByTotalRoomsCnt(int totalRoomsCnt);
}
