package com.university.accommodationmanager.controller;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.university.accommodationmanager.domain.Accomodation;
import com.university.accommodationmanager.service.AccomodationService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;

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
	
		
	@PostMapping(value = "/add", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	private ResponseEntity<String> addaccomodation(@RequestPart("accomodation") String accomodation,
												   @RequestPart("file") MultipartFile file){
		ObjectMapper mapper = new ObjectMapper();
	//	Accomodation accomodationObject=null;
//		try {
//			 accomodationObject = mapper.readValue(accomodation, Accomodation.class);
//		} catch (JsonProcessingException e) {
//			log.error("Error while converting to pojo in add accomdation method "+e);
//		}
//		try(Reader reader = new InputStreamReader(accomodation){
			Gson gson = new GsonBuilder().create();
			Accomodation accomodationObject = gson.fromJson(accomodation, Accomodation.class);
			System.out.println(accomodationObject);
//		}
		 accomodationService.addNewAccomodation(accomodationObject,file);
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
