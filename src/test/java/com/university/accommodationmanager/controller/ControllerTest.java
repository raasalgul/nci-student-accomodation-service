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

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {

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
        String request="";
        List<Accomodation> response=new ArrayList<>();
        Mockito.when(accomodationService.getAccomodation()).thenReturn(response);
        accomodationController.getaccomodation();
    }
}
