package com.appsdelevloper.app.ws.io.repositories;

import org.springframework.data.repository.CrudRepository;

import com.appsdelevloper.app.ws.io.entity.PasswordResetTokenEntity;

public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetTokenEntity, Long>{
	PasswordResetTokenEntity findByToken(String token);
}
