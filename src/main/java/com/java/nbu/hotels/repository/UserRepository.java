package com.java.nbu.hotels.repository;

import com.java.nbu.hotels.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
   UserEntity getUserById(int id);

   List<UserEntity> findUsersByRole(byte role);

   List<UserEntity> findUsersByName(String name);

   UserEntity findUserByEmail(String email);
}
