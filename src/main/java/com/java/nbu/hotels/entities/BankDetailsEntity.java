package com.java.nbu.hotels.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "BankDetails", schema = "Hotel", catalog = "")
public class BankDetailsEntity {
   private int id;
   private String cardNumber;
   private UserEntity user;

   @Id
   @Column(name = "id", nullable = false)
   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   @Basic
   @Column(name = "card_number", nullable = false, length = 150)
   public String getCardNumber() {
      return cardNumber;
   }

   public void setCardNumber(String cardNumber) {
      this.cardNumber = cardNumber;
   }

   @ManyToOne
   @JoinColumn(name = "user_id", nullable = false)
   public UserEntity getUser() {
      return user;
   }

   public void setUser(UserEntity user) {
      this.user = user;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      BankDetailsEntity that = (BankDetailsEntity) o;
      return id == that.id && Objects.equals(cardNumber, that.cardNumber);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, cardNumber);
   }
}
