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
	
	private TasksDTO MapToDTO(Tasks tasks)
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
	public TasksDTO CreateTask(Tasks tasks)
	{
		return this.MapToDTO(this.tasksRepo.save(tasks));
	}

	
	      //READ : Read All  
	
	   public List<TasksDTO> ReadAllTasks()
	   {
		   return this.tasksRepo.findAll().
				   stream().map(this::MapToDTO)
				   .collect(Collectors.toList());
		   //Streams : collect the tasks objects into a list data type and return them
	   }
	
	
	   //READ : Read byID
	
	public TasksDTO ReadByID(Long id)
	{
		return this.MapToDTO(this.tasksRepo.findById(id).orElseThrow());
	}
	
	
	//UPDATE
	public TasksDTO UpdateTask(TasksDTO tasksDTO, Long id)
	{
		Tasks dataToUpdate = this.tasksRepo.findById(id).orElseThrow();
		
		dataToUpdate.setName(tasksDTO.getName());
		
		SpringBeanUtil.MergeNotNull(tasksDTO, dataToUpdate);
		
		return this.MapToDTO(this.tasksRepo.save(dataToUpdate));
		
	}
	
	//DELETE
	
	public boolean DeleteTask(Long id)
	{
		this.tasksRepo.deleteById(id);
		// Return true If the id does not exist anymore
		return !this.tasksRepo.existsById(id);
	}
	
	
	
}


