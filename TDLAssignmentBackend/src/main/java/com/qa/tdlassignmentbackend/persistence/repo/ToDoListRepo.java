package com.qa.tdlassignmentbackend.persistence.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qa.tdlassignmentbackend.persistence.domain.ToDoList;

public interface ToDoListRepo extends JpaRepository<ToDoList,Long> {

	
	
	
	//Custom Queries 
	
	@Query(value ="SELECT * FROM TODOLIST WHERE COLOUR=?1", nativeQuery = true)
	List<ToDoList> findByColourCode(String Colour);
	
	@Query(value ="SELECT * FROM TODOLIST WHERE NAME=?1", nativeQuery = true)
	List<ToDoList> findByName(String Colour);
}
