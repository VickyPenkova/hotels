package com.java.nbu.hotels.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "booking", schema = "Hotel")
public class BookingEntity {
   private int idBooking;
   private Date startDate;
   private Date endDate;
   private String status;
   private UserEntity user;
   private Set<RoomEntity> rooms;

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "idbooking", nullable = false)
   public int getIdBooking() {
      return idBooking;
   }

   public void setIdBooking(int idbooking) {
      this.idBooking = idbooking;
   }

   @Basic
   @Column(name = "start_date", nullable = true)
   public Date getStartDate() {
      return startDate;
   }

   public void setStartDate(Date startDate) {
      this.startDate = startDate;
   }

   @Basic
   @Column(name = "end_date", nullable = true)
   public Date getEndDate() {
      return endDate;
   }

   public void setEndDate(Date endDate) {
      this.endDate = endDate;
   }

   @Basic
   @Column(name = "status", nullable = true, length = 150)
   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   @ManyToOne
   @JoinColumn(name="user_id", referencedColumnName = "id")
   public UserEntity getUser() {
      return user;
   }

   public void setUser(UserEntity user) {
      this.user = user;
   }

   @ManyToMany(mappedBy = "booking")
   public Set<RoomEntity> getRooms() {
      return rooms;
   }

   public void setRooms(Set<RoomEntity> rooms) {
      this.rooms = rooms;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      BookingEntity that = (BookingEntity) o;
      return idBooking == that.idBooking && Objects
            .equals(startDate, that.startDate) && Objects
            .equals(endDate, that.endDate) && Objects
            .equals(status, that.status);
   }

   @Override
   public int hashCode() {
      return Objects.hash(idBooking, startDate, endDate, status);
   }
}
