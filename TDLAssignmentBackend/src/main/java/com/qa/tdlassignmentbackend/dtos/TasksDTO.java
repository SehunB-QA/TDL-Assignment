package com.qa.tdlassignmentbackend.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class TasksDTO {
	//Lombok to automatically generate :) our constructors, ToString, getters & setters etc... 
	private Long id;
	private String name;
	private String description;
    private String colour;




}