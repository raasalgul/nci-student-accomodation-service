package com.university.accommodationmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.university.accommodationmanager.domain.Roommate;
import com.university.accommodationmanager.service.RoommateService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/roommate")
@Slf4j
@CrossOrigin
public class RoomMateController {

	@Autowired
	RoommateService roommateService;
	
	
	@GetMapping("/retrieve")
	private ResponseEntity<List<Roommate>> getRoommate(){
		List<Roommate> roommateList=roommateService.getRoommate();
		return new ResponseEntity<List<Roommate>>(roommateList,HttpStatus.OK);
	}
	
		
	@PostMapping("/add")
	private ResponseEntity<String> addRoommate(@RequestBody Roommate roommate){
		roommateService.addNewRoomMate(roommate);
		return new ResponseEntity<String>("Success",HttpStatus.OK);
	}
	
	@GetMapping("/filter/{column}/{value}")
	private  ResponseEntity<List<Roommate>> filterRoommate(@PathVariable String column,@PathVariable String value) {
		log.trace("call to retrieve room mate for column "+column +" with value"+ value);
		List<Roommate> roommateList=roommateService.filterRoomates(column, value);
		return new ResponseEntity<List<Roommate>>(roommateList,HttpStatus.OK);
	}
	
	@PutMapping("/status/{roomId}")
	private  ResponseEntity<String> updateAvailablity(@PathVariable String roomId) {
		log.trace("call to update closed status for room id "+roomId);
		roommateService.updateAvailablity(roomId);
		return new ResponseEntity<String>("Success",HttpStatus.OK);
	}
}
