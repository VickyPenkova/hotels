package com.java.nbu.hotels.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "Hotel")
public class UserEntity {
   private int id;
   private Byte role;
   private String name;
   private String pass;
   private String email;
   private boolean enabled;

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
   public Byte getRole() {
      return role;
   }

   public void setRole(Byte role) {
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
