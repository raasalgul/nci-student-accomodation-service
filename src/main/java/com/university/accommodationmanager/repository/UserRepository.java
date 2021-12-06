package com.university.accommodationmanager.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.university.accommodationmanager.domain.User;

import java.util.Optional;


@Repository
public interface UserRepository  extends MongoRepository<User,String>{
	Optional<User> findByUsername(String username);

	User findByEmail(String username);

	Boolean existsByUsername(String userName);

	Boolean existsByEmail(String email);
}
