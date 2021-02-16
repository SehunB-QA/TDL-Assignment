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
	public ResponseEntity<ToDoListDTO> create(@RequestBody ToDoList toDoList)
	{
		ToDoListDTO createdList = this.toDoListService.createList(toDoList);
		return new ResponseEntity<>(createdList, HttpStatus.CREATED); 
		
	}
	
	@GetMapping("/read")
	public ResponseEntity<List<ToDoListDTO>> readAll()
	{
		return  ResponseEntity.ok(this.toDoListService.readAllLists());
		
	}
	
	 // Custom Reads
	
	
	@GetMapping("/findbyname/{name}")
	public ResponseEntity<List<ToDoListDTO>> findByName(@PathVariable String name)
	{
		return ResponseEntity.ok(this.toDoListService.findByName(name));
		
	}
	
	
	///
	
	@GetMapping("/read/{id}")
	public ResponseEntity<ToDoListDTO> readSingleList(@PathVariable Long id)
	{
		return ResponseEntity.ok(this.toDoListService.readListByID(id));
		
	}
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ToDoListDTO> update(@PathVariable Long id, @RequestBody ToDoListDTO toDoListDTO)
	{
		
		return new ResponseEntity<>(this.toDoListService.updateList(toDoListDTO, id), HttpStatus.ACCEPTED);
		
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ToDoListDTO> delete(@PathVariable Long id)
	{
		
		return this.toDoListService.deleteEntireToDoList(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
		   //itenrary if
		
	}
	


}
