package com.university.accommodationmanager.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import  com.university.accommodationmanager.domain.Roommate;


@Repository
public interface RoommateRepository extends MongoRepository<Roommate,String>{


	List<Roommate> findByBudget(String value);

	List<Roommate> findByArea(String value);

	List<Roommate> findByEirCode(String value);

	List<Roommate> findAllByAvailablity(String available);

}
