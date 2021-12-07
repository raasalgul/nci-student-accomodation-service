package com.university.accommodationmanager.domain;

import java.util.List;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document
@NoArgsConstructor
public class Roommate {
	
	@Id
	private String id;
	
	private String userId;
	
	private List<String> area;
	
	private List<String> eirCode;
	
	private String duration;
	
	private String availablity;
	
	private String education;
	
	private String work;
	
	private Binary picture;
	
	private String budget;
	
}
