package com.java.nbu.hotels;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Room_Facilities", schema = "Hotel", catalog = "")
@IdClass(RoomFacilitiesEntityPK.class)
public class RoomFacilitiesEntity {
   private int roomId;
   private int facilityId;

   @Id
   @Column(name = "room_id", nullable = false)
   public int getRoomId() {
      return roomId;
   }

   public void setRoomId(int roomId) {
      this.roomId = roomId;
   }

   @Id
   @Column(name = "facility_id", nullable = false)
   public int getFacilityId() {
      return facilityId;
   }

   public void setFacilityId(int facilityId) {
      this.facilityId = facilityId;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      RoomFacilitiesEntity that = (RoomFacilitiesEntity) o;
      return roomId == that.roomId && facilityId == that.facilityId;
   }

   @Override
   public int hashCode() {
      return Objects.hash(roomId, facilityId);
   }
}
