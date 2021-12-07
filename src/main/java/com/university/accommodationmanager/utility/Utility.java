//package com.university.accommodationmanager.utility;
//
//import com.university.accommodationmanager.domain.User;
//import com.university.accommodationmanager.repository.UserRepository;
//import com.university.accommodationmanager.security.jwt.JwtUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Optional;
//
//@Component
//public class Utility {
//
//    @Autowired
//    JwtUtils jwtUtils;
//    public String getUserInfoFromBearer(String bearer){
////        =new JwtUtils();
//        bearer = bearer.replace("Bearer ", "");
//        String username = jwtUtils.getUserNameFromJwtToken(bearer);
//        return username;
//    }
//}
