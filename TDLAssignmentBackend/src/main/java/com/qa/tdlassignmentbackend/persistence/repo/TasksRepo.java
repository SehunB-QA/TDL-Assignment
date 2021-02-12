package com.qa.tdlassignmentbackend.persistence.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.tdlassignmentbackend.persistence.domain.Tasks;

@Repository
public interface TasksRepo extends JpaRepository<Tasks,Long> {

	
	
	


	//Custom Queries 
	
	@Query(value ="SELECT * FROM tasks WHERE COLOUR=?1", nativeQuery = true)
	List<Tasks> findByColourCode(String colour);
	
	@Query(value ="SELECT * FROM tasks WHERE NAME=?1", nativeQuery = true)
	List<Tasks> findByName(String name);
	
}
