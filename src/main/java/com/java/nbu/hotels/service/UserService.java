package com.java.nbu.hotels.service;

import com.java.nbu.hotels.entities.RoomEntity;
import com.java.nbu.hotels.entities.UserEntity;
import com.java.nbu.hotels.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
   @Autowired
   private UserRepository repo;

   public List<UserEntity> getUsers(){
      List<UserEntity> users = new ArrayList();
      repo.findAll().forEach(userEntity -> users.add(userEntity));
      return users;
   }

   public void registerUser(UserEntity user) {
      repo.save(user);
   }

   public void updateUserInfo(UserEntity user){
      repo.save(user);
   }

   public void deleteUser(int id){
      UserEntity userToDelete = repo.getUserById(id);
      repo.delete(userToDelete);
   }
}
