package com.university.accommodationmanager.controller;

import com.university.accommodationmanager.domain.Accomodation;
import com.university.accommodationmanager.domain.Roommate;
import com.university.accommodationmanager.service.AccomodationService;
import com.university.accommodationmanager.service.RoommateService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class RoomateControllerTest {

    @Mock
    RoommateService roommateService;
    @InjectMocks
    RoomMateController roomMateController =new RoomMateController();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void getAccomodationTest(){
        List<Roommate> mockResponse=new ArrayList<>();
        Mockito.when(roommateService.getRoommate()).thenReturn(mockResponse);
        ResponseEntity<List<Roommate>> response = roomMateController.getRoommate();
        assertEquals(mockResponse,response.getBody());
    }

    @Test
    public void addAccomodationTest(){
        String accomodation="{\"msg\":\"test\"}";
        Roommate mockResponse=new Roommate();
        MultipartFile file = null;
        Mockito.when(roommateService.addNewRoomMate(Mockito.any(),Mockito.any())).thenReturn(mockResponse);
        ResponseEntity<Roommate> response = roomMateController.addRoommate(accomodation,file);
        assertEquals(mockResponse,response.getBody());
    }

    @Test
    public void getUseraccomodation1Test(){
        Roommate mockResponse=new Roommate();
        Mockito.when(roommateService.getUserRoomate()).thenReturn(mockResponse);
        ResponseEntity<Roommate> response = roomMateController.getUserRoomate();
        assertEquals(mockResponse,response.getBody());
    }

}

