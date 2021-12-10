package com.university.accommodationmanager.controller;

import com.university.accommodationmanager.domain.Roommate;
import com.university.accommodationmanager.domain.User;
import com.university.accommodationmanager.service.RoommateService;
import com.university.accommodationmanager.service.impl.UserInfoService;
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
public class UserInfoControllerTest {

    @Mock
    UserInfoService userDetailsService;
    @InjectMocks
    UserInfoController userInfoController =new UserInfoController();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void getUserInfoTest(){
        User user=new User();
        Mockito.when(userDetailsService.getUserInfo()).thenReturn(user);
        ResponseEntity<?> response = userInfoController.getUserInfo();
        assertEquals(user,response.getBody());
    }

    @Test
    public void putUserInfoTest(){
        User user=new User();
        Mockito.when(userDetailsService.putUserInfo(Mockito.any())).thenReturn(user);
        ResponseEntity<?> response = userInfoController.putUserInfo(user);
        assertEquals(user,response.getBody());
    }

}

