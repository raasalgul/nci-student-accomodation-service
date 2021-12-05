package com.university.accommodationmanager.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.university.accommodationmanager.domain.User;


@Repository
public interface UserRepository  extends MongoRepository<User,String>{
	 User findByUsername(String username);

	User findByEmail(String username);
}
