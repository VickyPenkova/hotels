package com.java.nbu.hotels.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "ConformationToken", schema = "Hotel")
public class ConformationTokenEntity {
   private long tokenId;
   private String confirmationToken;
   private Date createdDate;
   private UserEntity user;

   public ConformationTokenEntity() {
   }

   public ConformationTokenEntity(UserEntity user) {
      this.user = user;
      createdDate = new Date();
      confirmationToken = UUID.randomUUID().toString();
   }

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "token_id", nullable = false)
   public long getTokenId() {
      return tokenId;
   }

   public void setTokenId(long tokenId) {
      this.tokenId = tokenId;
   }

   @Basic
   @Column(name = "confirmation_token", nullable = true, length = 255)
   public String getConfirmationToken() {
      return confirmationToken;
   }

   public void setConfirmationToken(String confirmationToken) {
      this.confirmationToken = confirmationToken;
   }

   @Basic
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "created_date", nullable = true)
   public Date getCreatedDate() {
      return createdDate;
   }

   public void setCreatedDate(Date createdDate) {
      this.createdDate = createdDate;
   }

   @OneToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER)
   @JoinColumn(nullable = false, name = "user_id")
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
      ConformationTokenEntity that = (ConformationTokenEntity) o;
      return tokenId == that.tokenId && Objects
            .equals(confirmationToken, that.confirmationToken) && Objects
            .equals(createdDate, that.createdDate);
   }

   @Override
   public int hashCode() {
      return Objects.hash(tokenId, confirmationToken, createdDate);
   }
}
