package com.university.accommodationmanager.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document
@NoArgsConstructor
public class Accomodation {
	
	@Id
	private String id;
	
	private String userId;
	
	private String area;
	
	private String eirCode;
	
	private String duration;
	
	private String availablity;
	
	private String education;
	
	private String work;
	
	private String picture;
	
	private String rent;
	
	private String description;
}
