package com.java.nbu.hotels.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "Booked", schema = "Hotel", catalog = "")
public class BookedEntity {
   private int id;
   private Date startDate;
   private Date endDate;
   private RoomEntity room;

   @ManyToOne
   @JoinColumn(name="room_id", referencedColumnName = "id", nullable = false)
   public RoomEntity getRoom() {
      return room;
   }

   public void setRoom(RoomEntity room) {
      this.room = room;
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
   @Column(name = "start_date", nullable = false)
   public Date getStartDate() {
      return startDate;
   }

   public void setStartDate(Date startDate) {
      this.startDate = startDate;
   }

   @Basic
   @Column(name = "end_date", nullable = false)
   public Date getEndDate() {
      return endDate;
   }

   public void setEndDate(Date endDate) {
      this.endDate = endDate;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      BookedEntity that = (BookedEntity) o;
      return id == that.id && Objects.equals(startDate, that.startDate)
            && Objects.equals(endDate, that.endDate);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, startDate, endDate);
   }
}
