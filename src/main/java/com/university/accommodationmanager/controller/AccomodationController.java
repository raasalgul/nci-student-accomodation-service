package com.university.accommodationmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.university.accommodationmanager.domain.Accomodation;
import com.university.accommodationmanager.service.AccomodationService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/accomodation")
@Slf4j
@CrossOrigin
public class AccomodationController {

	@Autowired
	AccomodationService accomodationService;
	
	
	@GetMapping("/retrieve")
	private ResponseEntity<List<Accomodation>> getaccomodation(){
		List<Accomodation> accomodationList=accomodationService.getAccomodation();
		return new ResponseEntity<List<Accomodation>>(accomodationList,HttpStatus.OK);
	}
	
		
	@PostMapping("/add")
	private ResponseEntity<String> addaccomodation(@RequestBody Accomodation accomodation){
		accomodationService.addNewAccomodation(accomodation);
		return new ResponseEntity<String>("Success",HttpStatus.CREATED);
	}
	
	@GetMapping("/filter/{column}/{value}")
	private  ResponseEntity<List<Accomodation>> filteraccomodation(@PathVariable String column,@PathVariable String value) {
		log.trace("call to retrieve accomodation for column "+column +" with value"+ value);
		List<Accomodation> accomodationList=accomodationService.filterRoomates(column, value);
		return new ResponseEntity<List<Accomodation>>(accomodationList,HttpStatus.OK);
	}
	
	@PutMapping("/status/{accId}")
	private  ResponseEntity<String> updateAvailablity(@PathVariable String accId) {
		log.trace("call to update closed status for room id "+accId);
		accomodationService.updateAvailablity(accId);
		return new ResponseEntity<String>("Success",HttpStatus.OK);
	}
}
