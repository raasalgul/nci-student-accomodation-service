package com.university.accommodationmanager.controller;

import com.university.accommodationmanager.domain.Accomodation;
import com.university.accommodationmanager.service.AccomodationService;
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
public class AccomodationControllerTest {

    @Mock
    AccomodationService accomodationService;
    @InjectMocks
    AccomodationController accomodationController =new AccomodationController();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void getAccomodationTest(){
        List<Accomodation> mockResponse=new ArrayList<>();
        Mockito.when(accomodationService.getAccomodation()).thenReturn(mockResponse);
        ResponseEntity<List<Accomodation>> response = accomodationController.getaccomodation();
        assertEquals(mockResponse,response.getBody());
    }

    @Test
    public void addAccomodationTest(){
        String accomodation="{\"msg\":\"test\"}";
        Accomodation mockResponse=new Accomodation();
        MultipartFile file = null;
        Mockito.when(accomodationService.addNewAccomodation(Mockito.any(),Mockito.any())).thenReturn(mockResponse);
        ResponseEntity<Accomodation> response = accomodationController.addaccomodation(accomodation,file);
        assertEquals(mockResponse,response.getBody());
    }

    @Test
    public void getUseraccomodation1Test(){
        Accomodation mockResponse=new Accomodation();
        Mockito.when(accomodationService.getUserAccomodation()).thenReturn(mockResponse);
        ResponseEntity<Accomodation> response = accomodationController.getUseraccomodation();
        assertEquals(mockResponse,response.getBody());
    }

}

