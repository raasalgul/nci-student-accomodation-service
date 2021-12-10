package com.university.accommodationmanager.controller;

import com.university.accommodationmanager.domain.Roommate;
import com.university.accommodationmanager.domain.User;
import com.university.accommodationmanager.repository.UserRepository;
import com.university.accommodationmanager.security.jwt.JwtUtils;
import com.university.accommodationmanager.service.SequenceGeneratorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    AuthenticationManager authenticationManager;

    @Mock
    JwtUtils jwtUtils;

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder encoder;

    @Mock
    private SequenceGeneratorService sequenceGeneratorService;

    @InjectMocks
    UserController userController =new UserController();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void signInTest(){
        Authentication authentication = null;
    Mockito.when(authenticationManager.authenticate(Mockito.any())).thenReturn(authentication);
    Mockito.when(jwtUtils.getUserNameFromJwtToken(Mockito.any())).thenReturn("jwt");
    }

    @Test
    public void signupTest1(){
        User singUp=new User();
        Mockito.when(userRepository.existsByUsername(Mockito.any())).thenReturn(true);
        userController.registerUser(singUp);
    }
    @Test
    public void signupTest2(){
        User singUp=new User();
        Mockito.when(userRepository.existsByEmail(Mockito.any())).thenReturn(true);
        userController.registerUser(singUp);
    }
    @Test
    public void signupTest3(){
        User singUp=new User();
        Mockito.when(userRepository.existsByUsername(Mockito.any())).thenReturn(true);
        Mockito.when(userRepository.existsByEmail(Mockito.any())).thenReturn(false);
        Mockito.when(encoder.encode(Mockito.any())).thenReturn("ok");
        Mockito.when(sequenceGeneratorService.generateSequence(Mockito.any())).thenReturn(1L);
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(singUp);
        userController.registerUser(singUp);
    }

}

