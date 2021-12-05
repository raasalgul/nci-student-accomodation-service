package com.university.accommodationmanager.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "User")
@NoArgsConstructor
public class User {
		  @Id
		  private String id;
		  private String username;
		  private String password;
		  private String email;
		  private String age;
		  private String phoneNumber;
		  private String services;
}
