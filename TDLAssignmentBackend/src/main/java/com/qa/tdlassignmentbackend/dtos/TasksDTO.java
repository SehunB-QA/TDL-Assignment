package com.qa.tdlassignmentbackend.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

//Lombok to automatically generate :) our constructors, ToString, getters & setters etc... 
@Data
@NoArgsConstructor 
public class TasksDTO {
	
	private Long taskID;
	
	private String name;
	private String description;
    private String colour;


public TasksDTO(String name, String description, String colour)
{
	this.name = name;
	this.description = description;
	this.colour = colour;
}

}