package com.university.accommodationmanager.service.impl;

import com.university.accommodationmanager.constants.AccomodationConstants;
import com.university.accommodationmanager.domain.Accomodation;
import com.university.accommodationmanager.domain.Roommate;
import com.university.accommodationmanager.domain.User;
import com.university.accommodationmanager.exception.NoRoomMateAvailableException;
import com.university.accommodationmanager.repository.AccomodationRepository;
import com.university.accommodationmanager.repository.RoommateRepository;
import com.university.accommodationmanager.repository.UserRepository;
import com.university.accommodationmanager.security.jwt.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
        return userInfo.get();
    }

    public User putUserInfo(User userUpdate) {
        String bearer = request.getHeader("Authorization");
        bearer = bearer.replace("Bearer ", "");
        String username = jwtUtils.getUserNameFromJwtToken(bearer);
        Optional<User> userInfo = Optional.ofNullable(repository.findByUsername(username).
                orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username)));
        Optional<User> user = repository.findById(userInfo.get().getId());
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
                Roommate newRoomate=new Roommate();
                newRoomate.setUserId(returnUser.getId());
                roommateRepository.save(newRoomate);
            }
            return returnUser;
        } else {
            log.error("No Matching User data available for room id" + user);
            throw new NoRoomMateAvailableException("No Matching Room Mate data available");
        }
    }
}
