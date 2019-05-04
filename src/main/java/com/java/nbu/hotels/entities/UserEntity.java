package com.java.nbu.hotels.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "User", schema = "Hotel", catalog = "")
public class UserEntity {
   private int id;
   private byte role;
   private String name;
   private String pass;
   private String email;
   private Set<OrderEntity> orders;
   private Set<BankDetailsEntity> banksDetails;
   private String confirmationToken;
   private boolean enabled;

   @OneToMany(mappedBy = "user")
   @JsonIgnore
   public Set<BankDetailsEntity> getBanksDetails() {
      return banksDetails;
   }

   public void setBanksDetails(Set<BankDetailsEntity> banksDetails) {
      this.banksDetails = banksDetails;
   }

   @OneToMany(mappedBy = "user")
   @JsonIgnore
   public Set<OrderEntity> getOrders() {
      return orders;
   }

   public void setOrders(Set<OrderEntity> orders) {
      this.orders = orders;
   }

   @Id
   @Column(name = "id", nullable = false)
   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   @Basic
   @Column(name = "role", nullable = false)
   public byte getRole() {
      return role;
   }

   public void setRole(byte role) {
      this.role = role;
   }

   @Basic
   @Column(name = "name", nullable = false, length = 150)
   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   @Basic
   @Column(name = "pass", nullable = false, length = 150)
   public String getPass() {
      return pass;
   }

   public void setPass(String pass) {
      this.pass = pass;
   }

   @Basic
   @Column(name = "email", nullable = false, length = 150)
   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   @Column(name = "confirmation_token")
   public String getConfirmationToken(){
      return this.confirmationToken;
   }

   public void setConfirmationToken(String confirmationToken) {
      this.confirmationToken = confirmationToken;
   }

   @Column(name = "enabled")
   public boolean getEnabled() {
      return enabled;
   }

   public void setEnabled(boolean value) {
      this.enabled = value;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      UserEntity that = (UserEntity) o;
      return id == that.id && role == that.role && Objects
            .equals(name, that.name) && Objects.equals(pass, that.pass)
            && Objects.equals(email, that.email);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, role, name, pass, email);
   }
}
