package com.java.nbu.hotels.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "RoomPricePerDate", schema = "Hotel", catalog = "")
public class RoomPricePerDateEntity {
   private int id;
   private BigDecimal price;
   private Date startPeriod;
   private Date endPeriod;
   private RoomEntity room;

   @Id
   @Column(name = "id", nullable = false)
   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   @Basic
   @Column(name = "price", nullable = false, precision = 2)
   public BigDecimal getPrice() {
      return price;
   }

   public void setPrice(BigDecimal price) {
      this.price = price;
   }

   @Basic
   @Column(name = "start_period", nullable = false)
   public Date getStartPeriod() {
      return startPeriod;
   }

   public void setStartPeriod(Date startPeriod) {
      this.startPeriod = startPeriod;
   }

   @Basic
   @Column(name = "end_period", nullable = false)
   public Date getEndPeriod() {
      return endPeriod;
   }

   public void setEndPeriod(Date endPeriod) {
      this.endPeriod = endPeriod;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      RoomPricePerDateEntity that = (RoomPricePerDateEntity) o;
      return id == that.id && Objects.equals(price, that.price) && Objects
            .equals(startPeriod, that.startPeriod) && Objects
            .equals(endPeriod, that.endPeriod);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, price, startPeriod, endPeriod);
   }

   @ManyToOne
   @JoinColumn(name = "room_id", referencedColumnName = "id", nullable = false)
   public RoomEntity getRoom() {
      return room;
   }

   public void setRoom(RoomEntity roomByRoomId) {
      this.room = roomByRoomId;
   }
}
