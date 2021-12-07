package com.university.accommodationmanager.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.university.accommodationmanager.domain.Accomodation;
import  com.university.accommodationmanager.domain.Roommate;


@Repository
public interface AccomodationRepository extends MongoRepository<Accomodation,String>{


	List<Accomodation> findByRent(String value);

	List<Accomodation> findByArea(String value);

	List<Accomodation> findByEirCode(String value);

	List<Accomodation> findAllByAvailablity(String available);

	Accomodation findByUserId(String value);

}
