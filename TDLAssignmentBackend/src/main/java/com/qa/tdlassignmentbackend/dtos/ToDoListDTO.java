package com.qa.tdlassignmentbackend.dtos;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class ToDoListDTO {
	
	//Lombok to automatically generate :) our constructors, ToString, getters & setters etc... 
	private Long id;
	private String name;
	
	private List<TasksDTO> tasks = new ArrayList<>();

}
