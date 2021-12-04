package com.university.accommodationmanager.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.accommodationmanager.constants.AccomodationConstants;
import com.university.accommodationmanager.domain.Roommate;
import com.university.accommodationmanager.exception.NoMatchForFilterException;
import com.university.accommodationmanager.exception.NoRoomMateAvailableException;
import com.university.accommodationmanager.repository.RoommateRepository;
import com.university.accommodationmanager.service.RoommateService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RoommateServiceImpl implements RoommateService{
	
	@Autowired
	RoommateRepository roommateRepository;
	
	
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
	public void addNewRoomMate(Roommate roommate) {
		roommate.setAvailablity(AccomodationConstants.AVAILABLE);
		roommateRepository.save(roommate);
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
