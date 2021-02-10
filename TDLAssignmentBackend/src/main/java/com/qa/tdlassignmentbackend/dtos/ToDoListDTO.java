package com.qa.tdlassignmentbackend.dtos;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

//Lombok to automatically generate :) our constructors, ToString, getters & setters etc... 
@Data
@NoArgsConstructor 
public class ToDoListDTO {
	
	private Long listID;
	private String name;
	
	private List<TasksDTO> tasks = new ArrayList<>();

}
