package com.qa.tdlassignmentbackend.persistence.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qa.tdlassignmentbackend.persistence.domain.Tasks;

@Repository
public interface TasksRepo extends JpaRepository<Tasks,Long> {

	
	
	


	//Custom Queries 
	
	@Query(value ="SELECT * FROM tasks WHERE COLOUR=?1", nativeQuery = true)
	 Optional <Tasks> findByColourCode(String colour);
	
	@Query(value ="SELECT * FROM tasks WHERE NAME=?1", nativeQuery = true)
	Optional <Tasks> findByName(String name);
	
	@Modifying 
	@Transactional 
	//Modifying Annotation to allows the query to change the state of the database
	@Query(value ="UPDATE TASKS SET todolist_id = null WHERE id =?1", nativeQuery = true)
	int removeTaskFromToDoList(Long taskID);
	
	
	
}
