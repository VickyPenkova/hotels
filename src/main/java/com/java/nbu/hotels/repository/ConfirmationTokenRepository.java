package com.java.nbu.hotels.repository;

import com.java.nbu.hotels.entities.ConfirmationToken;
import org.springframework.data.repository.CrudRepository;

public interface ConfirmationTokenRepository extends
      CrudRepository<ConfirmationToken, String> {
   ConfirmationToken findByConfirmationToken(String confirmationToken);
}
