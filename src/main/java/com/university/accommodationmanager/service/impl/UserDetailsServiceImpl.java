//package com.university.accommodationmanager.service.impl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import com.university.accommodationmanager.repository.UserRepository;
//import java.util.Arrays;
//import java.util.List;
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService{
//  @Autowired
//  private UserRepository repository;
//  @Override
//  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//	  List<com.university.accommodationmanager.domain.User> usera=repository.findAll();
//	  com.university.accommodationmanager.domain.User user=repository.findByEmail(username);
//    if(user == null) {
//    	throw new UsernameNotFoundException("User not found");
//    }
//    List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("user"));
//    return new User(user.getUsername(), user.getPassword(), authorities);
//  }
//}
