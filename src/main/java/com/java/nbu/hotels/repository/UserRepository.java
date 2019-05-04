package com.java.nbu.hotels.repository;

import com.java.nbu.hotels.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
   UserEntity getUserById(int id);

   List<UserEntity> findUsersByRole(boolean role);

   List<UserEntity> findUsersByName(String name);

   UserEntity findByEmailIgnoreCase(String emailId);

   Optional<UserEntity> findByEmail(String emailId);
}
