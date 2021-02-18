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
	
	private ToDoListDTO mapToDTO(ToDoList todolist)
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
public ToDoListDTO createList(ToDoList todoList)
{
	return this.mapToDTO(this.todoListRepo.save(todoList));
}


    //READ : Read All  

 public List<ToDoListDTO> readAllLists()
 {
	   return this.todoListRepo.findAll().
			   stream().map(this::mapToDTO)
			   .collect(Collectors.toList());
	   //Streams : collect the tasks objects into a list data type and return them
 }


 //READ : Read byID

public ToDoListDTO readListByID(Long id)
{
	return this.mapToDTO(this.todoListRepo.findById(id).orElseThrow());
}


//Custom Read

public ToDoListDTO findByName(String name)
{
	return this.mapToDTO(this.todoListRepo.findByName(name).orElseThrow());
	   //Streams : collect the tasks objects into a list data type and return them
}



//UPDATE
public ToDoListDTO updateList(ToDoListDTO toDoListDTO, Long id)
{
	ToDoList dataToUpdate = this.todoListRepo.findById(id).orElseThrow();
	
	dataToUpdate.setName(toDoListDTO.getName());
	
	SpringBeanUtil.mergeNotNull(toDoListDTO, dataToUpdate);
	
	return this.mapToDTO(this.todoListRepo.save(dataToUpdate));
	
}

//DELETE

public boolean deleteEntireToDoList(Long id)
{
	this.todoListRepo.deleteById(id);
	// Return true If the id does not exist anymore
	return !this.todoListRepo.existsById(id);
	
}



	
	
	
	
}
