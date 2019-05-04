package com.java.nbu.hotels.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDetailsImpl extends UserEntity implements UserDetails {
   public UserDetailsImpl(UserEntity user) {
      super(user);
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return getRoles()
            .stream()
            .map(role-> new SimpleGrantedAuthority("ROLE_"+this.getRole()))
            .collect(Collectors.toList());
   }

   @Override
   public String getPassword() {
      return super.getPass();
   }

   @Override
   public String getUsername() {
      return super.getEmail();
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }

   public Set<String> getRoles(){
      HashSet<String> roles = new HashSet<>();
      roles.add(this.getRole());
      return roles;
   }

}
