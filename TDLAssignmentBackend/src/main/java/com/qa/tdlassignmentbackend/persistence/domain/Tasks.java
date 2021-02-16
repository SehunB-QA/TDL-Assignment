package com.qa.tdlassignmentbackend.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Tasks {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String name;
	@NotNull
	private String description;
	@NotNull
    private String colour;
	
	@ManyToOne
	private ToDoList todolist = null;
	
	
	
	public Tasks(String name, String description, String colour)
	{
		super();
		this.name = name;
		this.description = description;
		this.colour = colour;
	}
	
	
	public Tasks(Long taskID, String name, String description, String colour)
	{
		super();
		this.id = taskID;
		this.name = name;
		this.description = description;
		this.colour = colour;
	}

}
