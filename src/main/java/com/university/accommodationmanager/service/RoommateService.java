package com.university.accommodationmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.university.accommodationmanager.domain.Roommate;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface RoommateService {

	public List<Roommate> getRoommate();
	
	public List<Roommate> filterRoomates(String column,String value);
	
	public Roommate addNewRoomMate(Roommate roommate, MultipartFile file);

	public void updateAvailablity(String roomId);

    Roommate getUserRoomate();
}
