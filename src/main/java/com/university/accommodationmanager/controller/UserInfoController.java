package com.university.accommodationmanager.controller;

import com.university.accommodationmanager.domain.User;
import com.university.accommodationmanager.security.jwt.JwtUtils;
import com.university.accommodationmanager.service.SequenceGeneratorService;
import com.university.accommodationmanager.service.impl.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nci/user")
@Slf4j
@CrossOrigin
public class UserInfoController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserInfoService userDetailsService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;



    @GetMapping("/get-info")
    public ResponseEntity<?> getUserInfo() {
        User userInfo=userDetailsService.getUserInfo();
        return new ResponseEntity<User>(userInfo, HttpStatus.OK);
    }

    @PutMapping("/put-info")
    public ResponseEntity<?> putUserInfo(@RequestBody User updateInfo) {
        User userInfo=userDetailsService.putUserInfo(updateInfo);
        return new ResponseEntity<User>(userInfo,HttpStatus.OK);
    }

}
