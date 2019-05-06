package com.java.nbu.hotels.entities;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user", schema = "Hotel")
public class UserEntity {
   public UserEntity() {
   }

   public UserEntity(UserEntity user) {
      this.id = user.getId();
      this.role = user.getRole();
      this.name = user.getName();
      this.pass = user.getPass();
      this.email = user.getEmail();
      this.enabled = user.getEnabled();
   }

   private int id;
   private String role;
   private String name;
   private String pass;
   private String email;
   private boolean enabled;
   private Set<BookingEntity> bookings;

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", nullable = false)
   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   @Basic
   @Column(name = "role", nullable = true)
   public String getRole() {
      return role;
   }

   public void setRole(String role) {
      this.role = role;
   }

   @Basic
   @Column(name = "name", nullable = true, length = 150)
   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   @Basic
   @Column(name = "pass", nullable = true, length = 150)
   public String getPass() {
      return pass;
   }

   public void setPass(String pass) {
      this.pass = pass;
   }

   @Basic
   @Column(name = "email", nullable = true, length = 150)
   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   @Basic
   @Column(name = "enabled", nullable = true)
   public boolean getEnabled() {
      return enabled;
   }

   public void setEnabled(boolean enabled) {
      this.enabled = enabled;
   }

   @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
   public Set<BookingEntity> getBookings() {
      return bookings;
   }

   public void setBookings(Set<BookingEntity> bookings) {
      this.bookings = bookings;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      UserEntity that = (UserEntity) o;
      return id == that.id && Objects.equals(role, that.role) && Objects
            .equals(name, that.name) && Objects.equals(pass, that.pass)
            && Objects.equals(email, that.email) && Objects
            .equals(enabled, that.enabled);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, role, name, pass, email, enabled);
   }
}
