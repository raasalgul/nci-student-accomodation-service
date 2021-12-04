package com.university.accommodationmanager.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.accommodationmanager.constants.AccomodationConstants;
import com.university.accommodationmanager.domain.Accomodation;
import com.university.accommodationmanager.exception.NoMatchForFilterException;
import com.university.accommodationmanager.exception.NoRoomMateAvailableException;
import com.university.accommodationmanager.exception.NoAccomodationAvailableException;
import com.university.accommodationmanager.repository.AccomodationRepository;
import com.university.accommodationmanager.service.AccomodationService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccomodationServiceImpl implements AccomodationService{
	
	@Autowired
	AccomodationRepository accomodationRepository;
	
	
	public List<Accomodation> getAccomodation() {
		
		List<Accomodation> AccomodationList=accomodationRepository.findAllByAvailablity(AccomodationConstants.AVAILABLE);
		log.info("Total accomodation size"+AccomodationList.size());
		if(AccomodationList.isEmpty()) {
			log.error("No Data for Accomodation in repo");
			throw new NoAccomodationAvailableException("No Data for Accomodation in repo");
		}
		return AccomodationList;
	}


	@Override
	public void addNewAccomodation(Accomodation accomodation) {
		accomodation.setAvailablity(AccomodationConstants.AVAILABLE);
		accomodationRepository.save(accomodation);
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
