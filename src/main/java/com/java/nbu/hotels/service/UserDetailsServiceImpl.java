package com.java.nbu.hotels.service;

import com.java.nbu.hotels.entities.UserDetailsImpl;
import com.java.nbu.hotels.entities.UserEntity;
import com.java.nbu.hotels.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
   @Autowired
   private UserRepository usersRepository;

   // Mind the GAP OPTIONAL USER -> old logic
   @Override
   public UserDetails loadUserByUsername(String userName) throws
         UsernameNotFoundException {
      //      Optional<UserEntity> optionalUser = usersRepository.findByEmail(userName);
      //      return Optional.ofNullable(optionalUser).orElseThrow(()->new UsernameNotFoundException("Username Not Found"))
      //            .map(UserDetailsImpl::new).get();
      UserEntity user = usersRepository.findByEmailIgnoreCase(userName);
      if(user == null){
         throw new UsernameNotFoundException(userName);
      }

      return new UserDetailsImpl(user);
   }
}
