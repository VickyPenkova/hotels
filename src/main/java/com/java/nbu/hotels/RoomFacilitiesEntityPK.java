package com.java.nbu.hotels;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class RoomFacilitiesEntityPK implements Serializable {
   private int roomId;
   private int facilityId;

   @Column(name = "room_id", nullable = false)
   @Id
   public int getRoomId() {
      return roomId;
   }

   public void setRoomId(int roomId) {
      this.roomId = roomId;
   }

   @Column(name = "facility_id", nullable = false)
   @Id
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
      RoomFacilitiesEntityPK that = (RoomFacilitiesEntityPK) o;
      return roomId == that.roomId && facilityId == that.facilityId;
   }

   @Override
   public int hashCode() {
      return Objects.hash(roomId, facilityId);
   }
}
