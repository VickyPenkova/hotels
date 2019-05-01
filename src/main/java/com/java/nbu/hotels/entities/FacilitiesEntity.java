package com.java.nbu.hotels.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Facilities", schema = "Hotel", catalog = "")
public class FacilitiesEntity {
   private int id;
   private String type;
   private Set<RoomEntity> rooms;

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

   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      FacilitiesEntity that = (FacilitiesEntity) o;
      return id == that.id && Objects.equals(type, that.type);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, type);
   }

   @ManyToMany
   @JoinTable(name = "room_facilities",
               joinColumns = @JoinColumn(name="facility_id"),
               inverseJoinColumns = @JoinColumn(name = "room_id"))
   public Set<RoomEntity> getRooms() {
      return rooms;
   }

   public void setRooms(Set<RoomEntity> rooms) {
      this.rooms = rooms;
   }
}
