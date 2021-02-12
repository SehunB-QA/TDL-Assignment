package com.qa.tdlassignmentbackend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.tdlassignmentbackend.dtos.ToDoListDTO;
import com.qa.tdlassignmentbackend.persistence.domain.ToDoList;
import com.qa.tdlassignmentbackend.service.ToDoListService;

@RestController
@CrossOrigin //Allows a external party to connect to the controller
@RequestMapping("/todolist")
public class ToDoListController {
	
	private ToDoListService toDoListService;
	
	

	public ToDoListController(ToDoListService toDoListService) {
		super();
		this.toDoListService = toDoListService;
	}

	@PostMapping("/create")
	public ResponseEntity<ToDoListDTO> Create(@RequestBody ToDoList toDoList)
	{
		ToDoListDTO createdList = this.toDoListService.CreateList(toDoList);
		return new ResponseEntity<>(createdList, HttpStatus.CREATED); 
		
	}
	
	@GetMapping("/read")
	public ResponseEntity<List<ToDoListDTO>> ReadAll()
	{
		return  ResponseEntity.ok(this.toDoListService.ReadAllLists());
		
	}
	
	 // Custom Reads
	
	
	@GetMapping("/findbyname/{name}")
	public ResponseEntity<List<ToDoListDTO>> FindByName(@PathVariable String name)
	{
		return ResponseEntity.ok(this.toDoListService.FindByName(name));
		
	}
	
	
	///
	
	@GetMapping("/read/{id}")
	public ResponseEntity<ToDoListDTO> ReadSingleList(@PathVariable Long id)
	{
		return ResponseEntity.ok(this.toDoListService.ReadListByID(id));
		
	}
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ToDoListDTO> Update(@PathVariable Long id, @RequestBody ToDoListDTO toDoListDTO)
	{
		
		return new ResponseEntity<>(this.toDoListService.UpdateList(toDoListDTO, id), HttpStatus.ACCEPTED);
		
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ToDoListDTO> Delete(@PathVariable Long id)
	{
		
		return this.toDoListService.DeleteEntireToDoList(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
		   //itenrary if
		
	}
	
	//Need to put in delete single task in ToDoList

}
