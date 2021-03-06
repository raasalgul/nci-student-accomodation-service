package com.university.accommodationmanager.controller;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.university.accommodationmanager.domain.Roommate;
import com.university.accommodationmanager.service.RoommateService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/nci/roommate")
@Slf4j
@CrossOrigin
public class RoomMateController {

	@Autowired
	RoommateService roommateService;
	
	
	@GetMapping("/retrieve")
	public ResponseEntity<List<Roommate>> getRoommate(){
		List<Roommate> roommateList=roommateService.getRoommate();
		return new ResponseEntity<List<Roommate>>(roommateList,HttpStatus.OK);
	}

	@PutMapping(value = "/add", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<Roommate> addRoommate(@RequestPart("roommate") String roommate,
												   @RequestPart("file") MultipartFile file) {
		ObjectMapper mapper = new ObjectMapper();
		Gson gson = new GsonBuilder().create();
		Roommate roomateObject = gson.fromJson(roommate, Roommate.class);
		System.out.println(roomateObject);
		Roommate roomateResponse= roommateService.addNewRoomMate(roomateObject,file);
		return new ResponseEntity<Roommate>(roomateResponse,HttpStatus.CREATED);
	}

	@GetMapping(value = "/get")
	public ResponseEntity<Roommate> getUserRoomate(){
		Roommate roomate=roommateService.getUserRoomate();
		return new ResponseEntity<Roommate>(roomate,HttpStatus.CREATED);
	}

}
