package com.university.accommodationmanager.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.university.accommodationmanager.domain.User;
import com.university.accommodationmanager.repository.UserRepository;
import com.university.accommodationmanager.security.jwt.JwtUtils;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.university.accommodationmanager.constants.AccomodationConstants;
import com.university.accommodationmanager.domain.Roommate;
import com.university.accommodationmanager.exception.NoMatchForFilterException;
import com.university.accommodationmanager.exception.NoRoomMateAvailableException;
import com.university.accommodationmanager.repository.RoommateRepository;
import com.university.accommodationmanager.service.RoommateService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Service
@Slf4j
public class RoommateServiceImpl implements RoommateService{
	
	@Autowired
	RoommateRepository roommateRepository;

	@Autowired
	HttpServletRequest request;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private UserRepository repository;

	public List<Roommate> getRoommate() {
		List<Roommate> roomMateList=roommateRepository.findAllByAvailablity(AccomodationConstants.AVAILABLE);
		if(roomMateList.isEmpty()) {
			log.error("No Data for Room Mate in repo");
			throw new NoRoomMateAvailableException("No Data avaialble for Room Mate");
		}
		return roomMateList;
	}

	@Override
	public void updateAvailablity(String roomId) {
		Optional<Roommate> roomMate=roommateRepository.findById(roomId);
		if(roomMate.isPresent()) {
			roomMate.get().setAvailablity("Closed");
			roommateRepository.save(roomMate.get());
		}else {
			log.error("No Matching Accomodation data avaialble for room id"+roomId);
			throw new NoRoomMateAvailableException("No Matching Room Mate data avaialble");
		}
	}

	@Override
	public Roommate getUserRoomate() {
		String bearer = request.getHeader("Authorization");
		bearer = bearer.replace("Bearer ", "");
		String username = jwtUtils.getUserNameFromJwtToken(bearer);
		Optional<User> userInfo = Optional.ofNullable(repository.findByUsername(username).
				orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username)));
		return roommateRepository.findByUserId(userInfo.get().getId());
	}

	@Override
	public Roommate addNewRoomMate(Roommate roommate, MultipartFile file) {
//		String bearer = request.getHeader("Authorization");
//		bearer = bearer.replace("Bearer ", "");
//		String username = jwtUtils.getUserNameFromJwtToken(bearer);
//		Optional<User> userInfo = Optional.ofNullable(repository.findByUsername(username).
//				orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username)));
//		roommate.setUserId(userInfo.get().getId());
//		Binary binary=null;
//		try {
//			binary=new Binary(BsonBinarySubType.BINARY, file.getBytes());
//			//accomodation.setPicture(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
//		} catch (IOException e) {
//			log.error("error while saving file in accomodation ");
//		}
//		roommate.setPicture(binary);
//		System.out.println(roommate);
//		roommate.setAvailablity(AccomodationConstants.AVAILABLE);
//		roommateRepository.save(roommate);

		roommate.setAvailablity(AccomodationConstants.AVAILABLE);
		String bearer = request.getHeader("Authorization");
		bearer = bearer.replace("Bearer ", "");
		String username = jwtUtils.getUserNameFromJwtToken(bearer);
		Optional<User> userInfo = Optional.ofNullable(repository.findByUsername(username).
				orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username)));
		roommate.setUserId(userInfo.get().getId());
		Binary binary=null;
		try {
			binary=new Binary(BsonBinarySubType.BINARY, file.getBytes());
			//accomodation.setPicture(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
		} catch (IOException e) {
			log.error("error while saving file in accomodation ");
		}
		roommate.setPicture(binary);
		System.out.println(roommate);
		roommate.setName(userInfo.get().getUsername());
		roommate.setAge(userInfo.get().getAge());
		roommate=roommateRepository.save(roommate);
		return roommate;
	}


	@Override
	public List<Roommate> filterRoomates(String column, String value) {
		
		List<Roommate> filteredRoommates = new ArrayList<Roommate>();
		switch(column) {	
		case AccomodationConstants.BUDGET: 
			filteredRoommates=roommateRepository.findByBudget(value); break;
		case AccomodationConstants.AREA: 
			filteredRoommates=roommateRepository.findByArea(value); break;
		case AccomodationConstants.EIRCODE: 
			filteredRoommates=roommateRepository.findByEirCode(value);break;
		default:
			log.error("No Matching column "+column+"found in repo ");
			throw new NoMatchForFilterException("No Matching column in repo");
		}
		if(filteredRoommates.isEmpty()) {
			log.error("No Matching Data for Selected Filter"+column+" and value "+value);
			throw new NoMatchForFilterException("No Matching Data for Selected Filter");
		}
		return filteredRoommates;
	}
}
