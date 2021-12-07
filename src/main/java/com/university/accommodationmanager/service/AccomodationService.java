package com.university.accommodationmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.university.accommodationmanager.domain.Accomodation;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface AccomodationService {

	public List<Accomodation> getAccomodation();
	
	public List<Accomodation> filterRoomates(String column,String value);
	
	public Accomodation addNewAccomodation(Accomodation Accomodation, MultipartFile file);

	void updateAvailablity(String accomodationId);

	Accomodation getUserAccomodation();
}
