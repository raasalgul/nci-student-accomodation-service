package com.university.accommodationmanager.controller;

import com.university.accommodationmanager.domain.User;
import com.university.accommodationmanager.service.impl.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nci/user")
@Slf4j
@CrossOrigin
public class UserInfoController {

    @Autowired
    UserInfoService userDetailsService;

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
