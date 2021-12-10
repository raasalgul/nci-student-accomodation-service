package com.university.accommodationmanager.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.university.accommodationmanager.constants.AccomodationConstants;
import com.university.accommodationmanager.domain.Accomodation;
import com.university.accommodationmanager.domain.Email;
import com.university.accommodationmanager.domain.Roommate;
import com.university.accommodationmanager.domain.User;
import com.university.accommodationmanager.exception.NoRoomMateAvailableException;
import com.university.accommodationmanager.repository.AccomodationRepository;
import com.university.accommodationmanager.repository.RoommateRepository;
import com.university.accommodationmanager.repository.UserRepository;
import com.university.accommodationmanager.security.jwt.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@Slf4j
public class UserInfoService {
    @Autowired
    private UserRepository repository;

    @Autowired
    HttpServletRequest request;

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    AccomodationRepository accomodationRepository;

    @Autowired
    RoommateRepository roommateRepository;


    public User getUserInfo() {
        String bearer = request.getHeader("Authorization");
        bearer = bearer.replace("Bearer ", "");
        String username = jwtUtils.getUserNameFromJwtToken(bearer);
        Optional<User> userInfo = Optional.ofNullable(repository.findByUsername(username).
                orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username)));
        User userInfoDetails = new User();
        if(userInfo.isPresent())
        {
            userInfoDetails = userInfo.get();
            userInfoDetails.setPassword(null);
        }
        return userInfoDetails;
    }

    public User putUserInfo(User userUpdate) {
        String bearer = request.getHeader("Authorization");
        bearer = bearer.replace("Bearer ", "");
        String username = jwtUtils.getUserNameFromJwtToken(bearer);
        Optional<User> userInfo = Optional.ofNullable(repository.findByUsername(username).
                orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username)));
        Optional<User> user = repository.findById(userInfo.isPresent()?userInfo.get().getId():"");
        if (user.isPresent()) {
            user.get().setAge(userUpdate.getAge());
            user.get().setServices(userUpdate.getServices());
            user.get().setPhoneNumber(userUpdate.getPhoneNumber());
            user.get().setCourse(userUpdate.getCourse());
            User returnUser = repository.save(user.get());

            if(returnUser.getServices().equals(AccomodationConstants.ACCOMMODATION)){
                Accomodation newAccomodation= accomodationRepository.findByUserId(returnUser.getId());
                if(newAccomodation==null) {
                    newAccomodation=new Accomodation();
                    newAccomodation.setUserId(returnUser.getId());
                    accomodationRepository.save(newAccomodation);
                }
            }
            else if(returnUser.getServices().equals(AccomodationConstants.ROOMATE)){
                Roommate newRoomate= roommateRepository.findByUserId(returnUser.getId());
                if(newRoomate==null) {
                    newRoomate = new Roommate();
                    newRoomate.setUserId(returnUser.getId());
                    roommateRepository.save(newRoomate);
                }
            }
            return returnUser;
        } else {
            log.error("No Matching User data available for room id" + user);
            throw new NoRoomMateAvailableException("No Matching Room Mate data available");
        }
    }

    public String sendEmail(String postedUserId){
        final String uri = "https://zcu7syrl1i.execute-api.us-east-1.amazonaws.com/prod";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        Gson gson = new GsonBuilder().create();
//        String json = gson.toJson(email);
//        JSONObject personJsonObject = new JSONObject(json);
//        personJsonObject.get(email);

        String bearer = request.getHeader("Authorization");
        bearer = bearer.replace("Bearer ", "");
        String username = jwtUtils.getUserNameFromJwtToken(bearer);
        Optional<User> userInfo = Optional.ofNullable(repository.findByUsername(username).
                orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username)));
        Optional<User> user = repository.findById(userInfo.isPresent()?userInfo.get().getId():"");
        String msgTemplate ="";
        String senderUserName="",desc="",phnumber="",senderEmail="";
        if(user.isPresent()){
            String userId = user.get().getId();
            String service = user.get().getServices();
            senderEmail=user.get().getEmail();
            phnumber= user.get().getPhoneNumber();
            senderUserName= user.get().getUsername();
            if(service.equals(AccomodationConstants.ACCOMMODATION)){
                Accomodation accomodation=accomodationRepository.findByUserId(userId);
                desc=accomodation.getDescription();
            }
            else if(service.equals(AccomodationConstants.ROOMATE)){
                Roommate roommate=roommateRepository.findByUserId(userId);
                desc=roommate.getDescription();
            }
        }
        user= repository.findById(postedUserId);
        msgTemplate ="Hi, "+user.get().getUsername()+"\n" +
                senderUserName+" is interested in your service" +
                "Please contact him if you like their following description: \n" +desc
                +" .\nContact information: "+senderEmail+" "
                +phnumber+"."+
                "\nThank You" +
                "\nNCI Accommodation";
        log.info("formatted message is "+msgTemplate);
        String toEmail=user.isPresent()?user.get().getEmail():"";
        Email email = new Email();
        email.setMessage(msgTemplate);
        email.setTo(toEmail);
        email.setSubject("NCI Accommodation Interested");
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.postForObject(uri,email, String.class);
       return result;
    }
}
