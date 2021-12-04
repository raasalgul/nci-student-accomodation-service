package com.university.accommodationmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.university.accommodationmanager.domain.Accomodation;

@Service
public interface AccomodationService {

	public List<Accomodation> getAccomodation();
	
	public List<Accomodation> filterRoomates(String column,String value);
	
	public void addNewAccomodation(Accomodation Accomodation);

	void updateAvailablity(String accomodationId);
	
}
