package com.qa.tdlassignmentbackend.persistence.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.sun.istack.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
@Entity
public class ToDoList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long listID;
	@NotNull
	private String name;
	
	@OneToMany(mappedBy ="todolist", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
    private List<Tasks> tasks;

	public ToDoList(String name) {
		super();
		this.name = name;
		
	}

	public ToDoList(Long listID, String name) {
		super();
		this.listID = listID;
		this.name = name;
	}
	
	
}
