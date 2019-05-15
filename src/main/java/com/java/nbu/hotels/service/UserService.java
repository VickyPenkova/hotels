package com.java.nbu.hotels.service;

import com.java.nbu.hotels.entities.UserEntity;
import com.java.nbu.hotels.repository.UserRepository;
import com.nimbusds.openid.connect.sdk.claims.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService{
   @Autowired
   private UserRepository repo;

   public List<UserEntity> getUsers(){
      List<UserEntity> users = new ArrayList();
      repo.findAll().forEach(userEntity -> users.add(userEntity));
      return users;
   }

   public void saveUser(UserEntity user) {
      repo.save(user);
   }

   public void updateUserInfo(UserEntity user){
      repo.save(user);
   }

   public void deleteUser(int id){
      UserEntity userToDelete = repo.getUserById(id);
      repo.delete(userToDelete);
   }

   public UserEntity findByEmail(String email) {
      return repo.findByEmailIgnoreCase(email);
   }

   public UserEntity findById(int id) {
      return repo.getUserById(id);
   }
}
