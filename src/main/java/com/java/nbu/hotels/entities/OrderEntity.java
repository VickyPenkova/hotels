package com.java.nbu.hotels.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Order", schema = "Hotel", catalog = "")
public class OrderEntity {
   private int id;
   private Timestamp date;
   private int status;
   private BankDetailsEntity bankDetailsByBankDetailsId;
   private UserEntity user;
   private RoomEntity room;

   @ManyToOne
   @JoinColumn(name="room_id", referencedColumnName = "id", nullable = false)
   public RoomEntity getRoom() {
      return room;
   }

   public void setRoom(RoomEntity room) {
      this.room = room;
   }

   @ManyToOne
   @JoinColumn(name="user_id", referencedColumnName = "id", nullable = false)
   public UserEntity getUser() {
      return user;
   }

   public void setUser(UserEntity user) {
      this.user = user;
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
   @Column(name = "date", nullable = false)
   public Timestamp getDate() {
      return date;
   }

   public void setDate(Timestamp date) {
      this.date = date;
   }

   @Basic
   @Column(name = "status", nullable = false)
   public int getStatus() {
      return status;
   }

   public void setStatus(int status) {
      this.status = status;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      OrderEntity that = (OrderEntity) o;
      return id == that.id && status == that.status && Objects
            .equals(date, that.date);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, date, status);
   }

   @ManyToOne
   @JoinColumn(name = "bank_details_id", referencedColumnName = "id", nullable = false)
   public BankDetailsEntity getBankDetailsByBankDetailsId() {
      return bankDetailsByBankDetailsId;
   }

   public void setBankDetailsByBankDetailsId(
         BankDetailsEntity bankDetailsByBankDetailsId) {
      this.bankDetailsByBankDetailsId = bankDetailsByBankDetailsId;
   }


}
