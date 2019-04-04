package com.java.nbu.hotels;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Room", schema = "Hotel", catalog = "")
public class RoomEntity {
   private int id;
   private String type;
   private int totalRoomsCnt;
   private Set<OrderEntity> orders;
   private Set<FacilitiesEntity> facilities;
   private Set<RoomPricePerDateEntity> roomPricesPerDate;

   @OneToMany(mappedBy = "room")
   public Set<RoomPricePerDateEntity> getRoomPricesPerDate() {
      return roomPricesPerDate;
   }

   public void setRoomPricesPerDate(
         Set<RoomPricePerDateEntity> roomPricesPerDate) {
      this.roomPricesPerDate = roomPricesPerDate;
   }

   @ManyToMany(mappedBy = "rooms")
   public Set<FacilitiesEntity> getFacilities() {
      return facilities;
   }

   public void setFacilities(Set<FacilitiesEntity> facilities) {
      this.facilities = facilities;
   }

   @OneToMany(mappedBy = "room")
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
   @Column(name = "type", nullable = false, length = 150)
   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   @Basic
   @Column(name = "total_rooms_cnt", nullable = false)
   public int getTotalRoomsCnt() {
      return totalRoomsCnt;
   }

   public void setTotalRoomsCnt(int freeRoomsCnt) {
      this.totalRoomsCnt = freeRoomsCnt;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      RoomEntity that = (RoomEntity) o;
      return id == that.id && totalRoomsCnt == that.totalRoomsCnt && Objects
            .equals(type, that.type);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, type, totalRoomsCnt);
   }
}
