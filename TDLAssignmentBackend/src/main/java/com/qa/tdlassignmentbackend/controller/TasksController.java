package com.qa.tdlassignmentbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.qa.tdlassignmentbackend.dtos.TasksDTO;
import com.qa.tdlassignmentbackend.persistence.domain.Tasks;
import com.qa.tdlassignmentbackend.service.TaskService;

@RestController
@CrossOrigin
@RequestMapping("/tasks")
public class TasksController {
	
	private TaskService taskService;

	@Autowired
	public TasksController(TaskService taskService) {
		super();
		this.taskService = taskService;
	}
	
	@PostMapping("/create")
	public ResponseEntity<TasksDTO> Create(@RequestBody Tasks tasks)
	{
		TasksDTO createdTask = this.taskService.CreateTask(tasks);
		return new ResponseEntity<>(createdTask, HttpStatus.CREATED); 
		
	}
	
	@GetMapping("/read")
	public ResponseEntity<List<TasksDTO>> ReadAll()
	{
		return  ResponseEntity.ok(this.taskService.ReadAllTasks());
		
	}
	
	 // Custom Reads
	
	@GetMapping("/findbycolourcode/{colour}")
	public ResponseEntity<List<TasksDTO>> FindByColourCode(@PathVariable String colour)
	{
		return  ResponseEntity.ok(this.taskService.FindByColourCode(colour));
		
	}

	@GetMapping("/findbyname/{name}")
	public ResponseEntity<List<TasksDTO>> FindByName(@PathVariable String name)
	{
		return  ResponseEntity.ok(this.taskService.FindByName(name));
		
	}
	
	
	
	
	
	///
	
	@GetMapping("/read/{id}")
	public ResponseEntity<TasksDTO> ReadSingleTask(@PathVariable Long id)
	{
		return ResponseEntity.ok(this.taskService.ReadByID(id));
		
	}
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<TasksDTO> Update(@PathVariable Long id, @RequestBody TasksDTO tasksDTO)
	{
		
		return new ResponseEntity<>(this.taskService.UpdateTask(tasksDTO, id), HttpStatus.ACCEPTED);
		
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<TasksDTO> Delete(@PathVariable Long id)
	{
		
		return this.taskService.DeleteTask(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
		   //itenrary if
		
	}
	
	
}
