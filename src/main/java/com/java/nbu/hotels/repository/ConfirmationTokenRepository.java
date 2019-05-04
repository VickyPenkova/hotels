package com.java.nbu.hotels.repository;
import com.java.nbu.hotels.entities.ConformationTokenEntity;
import org.springframework.data.repository.CrudRepository;

public interface ConfirmationTokenRepository extends CrudRepository<ConformationTokenEntity, String> {
   ConformationTokenEntity findByConfirmationToken(String confirmationToken);
}