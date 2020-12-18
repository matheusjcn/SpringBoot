package com.appsdeveloper.app.ws.io.repositories;

import org.springframework.data.repository.CrudRepository;

import com.appsdeveloper.app.ws.io.entity.AuthorityEntity;

public interface AuthoritoryRepository extends CrudRepository<AuthorityEntity, Long> {
	
	AuthorityEntity findByName(String name);
	
}
