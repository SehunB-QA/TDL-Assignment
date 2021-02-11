package com.qa.tdlassignmentbackend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.tdlassignmentbackend.dtos.ToDoListDTO;
import com.qa.tdlassignmentbackend.persistence.domain.ToDoList;
import com.qa.tdlassignmentbackend.persistence.repo.ToDoListRepo;
import com.qa.tdlassignmentbackend.utils.SpringBeanUtil;

@Service
public class ToDoListService {


	private ToDoListRepo todoListRepo;
	
	private ModelMapper mapper; 
	
	private ToDoListDTO MapToDTO(ToDoList todolist)
	{
		return this.mapper.map(todolist,ToDoListDTO.class);
	}
	
    @Autowired
	public ToDoListService(ToDoListRepo todoListRepo, ModelMapper mapper) {
		super();
		this.todoListRepo = todoListRepo;
		this.mapper = mapper;
	}
	
    

    /// CRUD \\\\

      //CREATE
public ToDoListDTO CreateTask(ToDoList todoList)
{
	return this.MapToDTO(this.todoListRepo.save(todoList));
}


    //READ : Read All  

 public List<ToDoListDTO> ReadAllTasks()
 {
	   return this.todoListRepo.findAll().
			   stream().map(this::MapToDTO)
			   .collect(Collectors.toList());
	   //Streams : collect the tasks objects into a list data type and return them
 }


 //READ : Read byID

public ToDoListDTO ReadByID(Long id)
{
	return this.MapToDTO(this.todoListRepo.findById(id).orElseThrow());
}

//Custom Reads 

public List<ToDoListDTO> FindByColourCode(String colour)
{
	return this.todoListRepo.findByColourCode(colour).
			   stream().map(this::MapToDTO)
			   .collect(Collectors.toList());
	   //Streams : collect the tasks objects into a list data type and return them
}

public List<ToDoListDTO> FindByName(String name)
{
	return this.todoListRepo.findByName(name).
			   stream().map(this::MapToDTO)
			   .collect(Collectors.toList());
	   //Streams : collect the tasks objects into a list data type and return them
}




//UPDATE
public ToDoListDTO UpdateTask(ToDoListDTO toDoListDTO, Long id)
{
	ToDoList dataToUpdate = this.todoListRepo.findById(id).orElseThrow();
	
	dataToUpdate.setName(toDoListDTO.getName());
	
	SpringBeanUtil.MergeNotNull(toDoListDTO, dataToUpdate);
	
	return this.MapToDTO(this.todoListRepo.save(dataToUpdate));
	
}

//DELETE

public boolean DeleteEntireToDoList(Long id)
{
	this.todoListRepo.deleteById(id);
	// Return true If the id does not exist anymore
	return !this.todoListRepo.existsById(id);
	
}


	// delete task from ToDoList
	
	
	
	
}
