package com.businessbook.domain.users.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.businessbook.domain.users.model.User;

@Repository
public interface UsersRepository extends MongoRepository<User, String>{
	User findByUsername(String username);
}
