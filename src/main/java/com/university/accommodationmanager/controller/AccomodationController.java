package com.university.accommodationmanager.controller;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.university.accommodationmanager.domain.Accomodation;
import com.university.accommodationmanager.service.AccomodationService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/nci/accomodation")
@Slf4j
@CrossOrigin
public class AccomodationController {

	@Autowired
	AccomodationService accomodationService;
	
	
	@GetMapping("/retrieve")
	public ResponseEntity<List<Accomodation>> getaccomodation(){
		List<Accomodation> accomodationList=accomodationService.getAccomodation();
		return new ResponseEntity<List<Accomodation>>(accomodationList,HttpStatus.OK);
	}
	
		
	@PutMapping(value = "/add")
	public ResponseEntity<Accomodation> addaccomodation(@RequestPart("accomodation") String accomodation,
												   @RequestPart("file") MultipartFile file){
		ObjectMapper mapper = new ObjectMapper();
			Gson gson = new GsonBuilder().create();
			Accomodation accomodationObject = gson.fromJson(accomodation, Accomodation.class);
			System.out.println(accomodationObject);
		Accomodation accomodationReponse= accomodationService.addNewAccomodation(accomodationObject,file);
		return new ResponseEntity<Accomodation>(accomodationReponse,HttpStatus.CREATED);
	}

	@GetMapping(value = "/get")
	public ResponseEntity<Accomodation> getUseraccomodation(){
		Accomodation accomodation=accomodationService.getUserAccomodation();
		return new ResponseEntity<Accomodation>(accomodation,HttpStatus.CREATED);
	}

}
