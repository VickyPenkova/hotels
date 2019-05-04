package com.java.nbu.hotels.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "room", schema = "Hotel")
public class RoomEntity {
   private int roomid;
   private String type;
   private Double price;
   Set<BookingEntity> booking;

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "roomid", nullable = false)
   public int getRoomid() {
      return roomid;
   }

   public void setRoomid(int roomid) {
      this.roomid = roomid;
   }

   @Basic
   @Column(name = "type", nullable = true, length = 150)
   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   @Basic
   @Column(name = "price", nullable = true, precision = 0)
   public Double getPrice() {
      return price;
   }

   public void setPrice(Double price) {
      this.price = price;
   }

   @ManyToMany(cascade = CascadeType.ALL)
   @JoinTable(name = "room_has_booking", joinColumns = @JoinColumn(name="room_roomid", referencedColumnName = "roomid"),
   inverseJoinColumns = @JoinColumn(name="booking_idbooking",referencedColumnName = "idbooking"))
   public Set<BookingEntity> getBooking() {
      return booking;
   }

   public void setBooking(Set<BookingEntity> booking) {
      this.booking = booking;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      RoomEntity that = (RoomEntity) o;
      return roomid == that.roomid && Objects.equals(type, that.type) && Objects
            .equals(price, that.price);
   }

   @Override
   public int hashCode() {
      return Objects.hash(roomid, type, price);
   }
}
