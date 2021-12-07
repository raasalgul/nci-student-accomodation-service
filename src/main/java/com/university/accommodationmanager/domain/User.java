package com.university.accommodationmanager.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "User")
@NoArgsConstructor
public class User {
	@Transient
	public static final String SEQUENCE_NAME = "users_sequence";
		  @Id
		  private String id;
		  private String username;
		  private String password;
		  private String email;
		  private String age;
		  private String phoneNumber;
		  private String services;
		  private String course;

	public User(String email, String encode, String username) {
		this.email=email;
		this.username=username;
		this.password=encode;
	}
}
