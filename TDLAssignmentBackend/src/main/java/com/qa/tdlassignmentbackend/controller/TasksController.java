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
	public ResponseEntity<TasksDTO> create(@RequestBody Tasks tasks)
	{
		TasksDTO createdTask = this.taskService.createTask(tasks);
		return new ResponseEntity<>(createdTask, HttpStatus.CREATED); 
		
	}
	
	@GetMapping("/read")
	public ResponseEntity<List<TasksDTO>> readAll()
	{
		return ResponseEntity.ok(this.taskService.readAllTasks());
		
	}
	
	 // Custom Reads
	
	@GetMapping("/findbycolourcode/{colour}")
	public ResponseEntity <TasksDTO> findByColourCode(@PathVariable String colour)
	{
		return  ResponseEntity.ok(this.taskService.findByColourCode(colour));
		
	}

	@GetMapping("/findbyname/{name}")
	public ResponseEntity <TasksDTO> findByName(@PathVariable String name)
	{
		return  ResponseEntity.ok(this.taskService.findByName(name));
		
	}
	
	
	
	
	
	///
	
	@GetMapping("/read/{id}")
	public ResponseEntity<TasksDTO> readSingleTask(@PathVariable Long id)
	{
		return ResponseEntity.ok(this.taskService.readByID(id));
		
	}
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<TasksDTO> update(@PathVariable Long id, @RequestBody TasksDTO tasksDTO)
	{
		
		return new ResponseEntity<>(this.taskService.updateTask(tasksDTO, id), HttpStatus.ACCEPTED);
		
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<TasksDTO> delete(@PathVariable Long id)
	{
		
		return this.taskService.deleteTask(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
		   //itenrary if
		
	}
	
	// Custom if the user wants to delete a task from a list but not the whole task itself
	//Updates the task reference to a joined list by setting the relationship task to list to null
	@PutMapping("/removefromlist/{taskID}")
	public ResponseEntity<TasksDTO> removeTaskFromToDoList(@PathVariable Long taskID)
	{
		
		return this.taskService.removeTaskFromToDoList(taskID) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
		   //itenrary if
		
	}
	
	
}
