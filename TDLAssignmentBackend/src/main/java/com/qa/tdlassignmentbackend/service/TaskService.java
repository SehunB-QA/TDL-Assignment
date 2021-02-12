package com.qa.tdlassignmentbackend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.tdlassignmentbackend.dtos.TasksDTO;
import com.qa.tdlassignmentbackend.persistence.domain.Tasks;
import com.qa.tdlassignmentbackend.persistence.repo.TasksRepo;
import com.qa.tdlassignmentbackend.utils.SpringBeanUtil;

@Service
public class TaskService {
	
	private TasksRepo tasksRepo;
	
	private ModelMapper mapper; 
	
	private TasksDTO mapToDTO(Tasks tasks)
	{
		return this.mapper.map(tasks,TasksDTO.class);
	}
	
    @Autowired
	public TaskService(TasksRepo tasksRepo, ModelMapper mapper) {
		super();
		this.tasksRepo = tasksRepo;
		this.mapper = mapper;
	}
	
    
          /// CRUD \\\\
    
	        //CREATE
	public TasksDTO createTask(Tasks tasks)
	{
		return this.mapToDTO(this.tasksRepo.save(tasks));
	}

	
	      //READ : Read All  
	
	   public List<TasksDTO> readAllTasks()
	   {
		   return this.tasksRepo.findAll().
				   stream().map(this::mapToDTO)
				   .collect(Collectors.toList());
		   //Streams : collect the tasks objects into a list data type and return them
	   }
	
	
	   //READ : Read byID
	
	public TasksDTO readByID(Long id)
	{
		return this.mapToDTO(this.tasksRepo.findById(id).orElseThrow());
	}
	
	
	//Custom Reads 

	public List<TasksDTO> findByColourCode(String colour)
	{
		return this.tasksRepo.findByColourCode(colour).
				   stream().map(this::mapToDTO)
				   .collect(Collectors.toList());
		   //Streams : collect the tasks objects into a list data type and return them
	}

	public List<TasksDTO> findByName(String name)
	{
		return this.tasksRepo.findByName(name).
				   stream().map(this::mapToDTO)
				   .collect(Collectors.toList());
		   //Streams : collect the tasks objects into a list data type and return them
	}
	
	
	//UPDATE
	public TasksDTO updateTask(TasksDTO tasksDTO, Long id)
	{
		Tasks dataToUpdate = this.tasksRepo.findById(id).orElseThrow();
		
		dataToUpdate.setName(tasksDTO.getName());
		
		SpringBeanUtil.mergeNotNull(tasksDTO, dataToUpdate);
		
		return this.mapToDTO(this.tasksRepo.save(dataToUpdate));
		
	}
	
	//DELETE
	
	public boolean deleteTask(Long id)
	{
		this.tasksRepo.deleteById(id);
		// Return true If the id does not exist anymore
		return !this.tasksRepo.existsById(id);
	}
	
	
	
}


