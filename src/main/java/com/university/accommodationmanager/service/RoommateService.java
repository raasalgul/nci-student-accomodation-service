package com.university.accommodationmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.university.accommodationmanager.domain.Roommate;

@Service
public interface RoommateService {

	public List<Roommate> getRoommate();
	
	public List<Roommate> filterRoomates(String column,String value);
	
	public void addNewRoomMate(Roommate roommate);

	public void updateAvailablity(String roomId);
	
}
