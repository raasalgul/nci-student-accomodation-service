package com.university.accommodationmanager.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.university.accommodationmanager.domain.User;
import com.university.accommodationmanager.repository.UserRepository;
//import com.university.accommodationmanager.utility.Utility;
import com.university.accommodationmanager.security.jwt.JwtUtils;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.university.accommodationmanager.constants.AccomodationConstants;
import com.university.accommodationmanager.domain.Accomodation;
import com.university.accommodationmanager.exception.NoMatchForFilterException;
import com.university.accommodationmanager.exception.NoRoomMateAvailableException;
import com.university.accommodationmanager.exception.NoAccomodationAvailableException;
import com.university.accommodationmanager.repository.AccomodationRepository;
import com.university.accommodationmanager.service.AccomodationService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Service
@Slf4j
public class AccomodationServiceImpl implements AccomodationService{
	
	@Autowired
	AccomodationRepository accomodationRepository;

	@Autowired
	private UserRepository repository;

	@Autowired
	HttpServletRequest request;

	@Autowired
	private JwtUtils jwtUtils;


	public List<Accomodation> getAccomodation() {
		
		List<Accomodation> AccomodationList=accomodationRepository.findAllByAvailablity(AccomodationConstants.AVAILABLE);
		log.info("Total accomodation size"+AccomodationList.size());
		AccomodationList.stream().forEach(value->{
			log.info("Values "+ value);
		});

		if(AccomodationList.isEmpty()) {
			log.error("No Data for Accomodation in repo");
			throw new NoAccomodationAvailableException("No Data for Accomodation in repo");
		}
		return AccomodationList;
	}


	@Override
	public Accomodation addNewAccomodation(Accomodation accomodation, MultipartFile file) {
		accomodation.setAvailablity(AccomodationConstants.AVAILABLE);
		String bearer = request.getHeader("Authorization");
		bearer = bearer.replace("Bearer ", "");
		String username = jwtUtils.getUserNameFromJwtToken(bearer);
		Optional<User> userInfo = Optional.ofNullable(repository.findByUsername(username).
				orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username)));
		accomodation.setUserId(userInfo.get().getId());
		Binary binary=null;
		try {
			binary=new Binary(BsonBinarySubType.BINARY, file.getBytes());
			//accomodation.setPicture(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
		} catch (IOException e) {
			log.error("error while saving file in accomodation ");
		}
		accomodation.setPicture(binary);
		System.out.println(accomodation);
		accomodation.setName(userInfo.get().getUsername());
		accomodation.setAge(userInfo.get().getAge());
		accomodation=accomodationRepository.save(accomodation);
		return accomodation;
	}
	
	@Override
	public void updateAvailablity(String accomodationId) {
		Optional<Accomodation> accOptional=accomodationRepository.findById(accomodationId);
		
		if(accOptional.isPresent()) {
			accOptional.get().setAvailablity("Closed");
			accomodationRepository.save(accOptional.get());
		}else {
			log.error("No Matching Accomodation data avaialble for accomodation id"+accomodationId);
			throw new NoRoomMateAvailableException("No Matching Accomodation data avaialble");
		}
	}

	@Override
	public Accomodation getUserAccomodation() {
		String bearer = request.getHeader("Authorization");
		bearer = bearer.replace("Bearer ", "");
		String username = jwtUtils.getUserNameFromJwtToken(bearer);
		Optional<User> userInfo = Optional.ofNullable(repository.findByUsername(username).
				orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username)));
		return accomodationRepository.findByUserId(userInfo.get().getId());
	}


	@Override
	public List<Accomodation> filterRoomates(String column, String value) {
		List<Accomodation> filteredAccomodations = new ArrayList<Accomodation>();
		switch(column) {	
		case AccomodationConstants.RENT: 
			filteredAccomodations=accomodationRepository.findByRent(value); break;
		case AccomodationConstants.AREA: 
			filteredAccomodations=accomodationRepository.findByArea(value); break;
		case AccomodationConstants.EIRCODE: 
			filteredAccomodations=accomodationRepository.findByEirCode(value);break;
		default:
			log.error("No Matching column "+column+"found in repo ");
			throw new NoMatchForFilterException("No Matching column in repo");
		}
		if(filteredAccomodations.isEmpty()) {
			log.error("No Matching Data for Selected Filter"+column+" and value "+value);
			throw new NoMatchForFilterException("No Matching Data for Selected Filter");
		}
		return filteredAccomodations;
	}
}
