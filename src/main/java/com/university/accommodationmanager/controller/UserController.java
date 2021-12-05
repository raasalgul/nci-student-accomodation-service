package com.university.accommodationmanager.controller;

import com.university.accommodationmanager.domain.Roommate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin
public class UserController {
    @GetMapping("/auth")
    private ResponseEntity<String> getHealth(){
        return new ResponseEntity<String>("Healthy", HttpStatus.OK);
    }

}
