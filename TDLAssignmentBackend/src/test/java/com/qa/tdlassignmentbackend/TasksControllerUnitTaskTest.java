package com.qa.tdlassignmentbackend;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.tdlassignmentbackend.controller.TasksController;
import com.qa.tdlassignmentbackend.dtos.TasksDTO;
import com.qa.tdlassignmentbackend.persistence.domain.Tasks;
import com.qa.tdlassignmentbackend.service.TaskService;


@SpringBootTest
public class TasksControllerUnitTaskTest {
	
	    @Autowired
		private TasksController controller;
	    
	    @MockBean
	    private TaskService tasksService;
	 
	    
	    @Autowired
	    private ModelMapper mapper;
		
	    private final Tasks testTask = new Tasks(2L, "Clean Dishes", "Use washing liquid", "Red");
	    private final List<Tasks> fullTasks = List.of(testTask);
	    
	    private TasksDTO mapToDTO(Tasks task)
	    {
	    	return this.mapper.map(task, TasksDTO.class);
	    }
		

		
		
		@Test
		public void createTest() throws Exception 
		{
			// WHEN
			
			when(this.tasksService.createTask(testTask)).thenReturn(this.mapToDTO(testTask));
			
			// THIS
			assertThat(new ResponseEntity<TasksDTO>(this.mapToDTO(testTask), HttpStatus.CREATED))
			.isEqualTo(this.controller.create(testTask));
				
			//System.out.println("Expected:" + testToDoList);
			//System.out.println("Result:" + controller.create(testToDoList));
		    verify(this.tasksService, atLeastOnce()).createTask(testTask);
		    		
		}
		
		
		@Test 
		public void readTest() throws Exception
	    {
			
			List<TasksDTO> testReadList =  fullTasks.stream().map(this:: mapToDTO).
					collect(Collectors.toList());
					
			when(this.tasksService.readAllTasks()).thenReturn(testReadList);
			ResponseEntity<List<TasksDTO>> expected = ResponseEntity.ok(testReadList);
			ResponseEntity<List<TasksDTO>> result = this.controller.readAll();
			assertEquals(expected, result);
			//System.out.println("Expected:" + expected);
			//System.out.println("Result:" + result);
		

		    verify(this.tasksService, atLeastOnce()).readAllTasks();
	    }
		
		
		@Test 
		public void readTaskByIDTest() throws Exception
	    {
			 Long testID = 2L;
			 TasksDTO testReadIDTask = this.mapToDTO(testTask);
			 
			when(this.tasksService.readByID(testID)).thenReturn(testReadIDTask); 
			ResponseEntity <TasksDTO> expected = ResponseEntity.ok(testReadIDTask);
			ResponseEntity <TasksDTO> result = this.controller.readSingleTask(testID);
			assertEquals(expected, result);
			

		    verify(this.tasksService, atLeastOnce()).readByID(testID);
	    }
		
		
		@Test 
		public void findTaskByNameTest() throws Exception
	    {
			 String testName = "Clean Dishes";
			 TasksDTO testReadNameTask = this.mapToDTO(testTask);
			when(this.tasksService.findByName(testName)).thenReturn(testReadNameTask);
			ResponseEntity <TasksDTO> expected = ResponseEntity.ok(testReadNameTask);
			ResponseEntity <TasksDTO> result = this.controller.findByName(testName);
			assertEquals(expected, result);
				//System.out.println("Expected:" + expected);
				//System.out.println("Result:" + result);

		    verify(this.tasksService, atLeastOnce()).findByName(testName);
	    }
		
		
		@Test 
		public void findTaskByColourTest() throws Exception
	    {
			 String testColour = "Red";
			 TasksDTO testReadColourTask = this.mapToDTO(testTask);
			when(this.tasksService.findByColourCode(testColour)).thenReturn(testReadColourTask);
			ResponseEntity <TasksDTO> expected = ResponseEntity.ok(testReadColourTask);
			ResponseEntity <TasksDTO> result = this.controller.findByColourCode(testColour);
			assertEquals(expected, result);
				//System.out.println("Expected:" + expected);
				//System.out.println("Result:" + result);

		    verify(this.tasksService, atLeastOnce()).findByColourCode(testColour);
	    }
		
		
		
		
		
		@Test
		public void updateTaskTest() throws Exception
	    {
			
			
			Long testID = 2L;
			 TasksDTO testUpdateTask = this.mapToDTO(testTask);
			 TasksDTO newTask = new TasksDTO();
			 newTask.setId(testID);
			 newTask.setName("Updated Task name");
			
			 when(this.tasksService.updateTask(testUpdateTask, testID)).thenReturn(newTask);
			 ResponseEntity <TasksDTO> expected = new ResponseEntity<>(newTask, HttpStatus.ACCEPTED);
				ResponseEntity <TasksDTO> result = this.controller.update(testID, testUpdateTask);
		
				assertEquals(expected,result);
			  verify(this.tasksService, atLeastOnce()).updateTask(testUpdateTask, testID);
	    }
		
		
		@Test
		public void deleteTaskTest() throws Exception
	    {
			Long testID = 2L;
			 when(this.tasksService.deleteTask(testID)).thenReturn(true);
			 ResponseEntity <TasksDTO> expected = new ResponseEntity<>(HttpStatus.NO_CONTENT);
			 ResponseEntity <TasksDTO> result = this.controller.delete(testID);
			 System.out.println(expected);
			 System.out.println(result);
			 
			 assertEquals(expected,result);
			  verify(this.tasksService, atLeastOnce()).deleteTask(testID);
			  
			  
			  
				
				 when(this.tasksService.deleteTask(testID)).thenReturn(false);
				 ResponseEntity <TasksDTO> expected2 = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				 ResponseEntity <TasksDTO> result2 = this.controller.delete(testID);
				 System.out.println(expected2);
				 System.out.println(result2);
				 
				 assertEquals(expected,result);
				  verify(this.tasksService, atLeastOnce()).deleteTask(testID);
			
		}
		
		
		@Test
		public void removeTaskFromListTest() throws Exception
	    {
			Long testID = 2L;
			 when(this.tasksService.removeTaskFromToDoList(testID)).thenReturn(true);
			 ResponseEntity <TasksDTO> expected = new ResponseEntity<>(HttpStatus.NO_CONTENT);
			 ResponseEntity <TasksDTO> result = this.controller.removeTaskFromToDoList(testID);
			 System.out.println(expected);
			 System.out.println(result);
			 
			 assertEquals(expected,result);
			  verify(this.tasksService, atLeastOnce()).removeTaskFromToDoList(testID);
			  
			  
			  
				
				 
				 when(this.tasksService.removeTaskFromToDoList(testID)).thenReturn(false);
				 ResponseEntity <TasksDTO> expected2 = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				 ResponseEntity <TasksDTO> result2 = this.controller.removeTaskFromToDoList(testID);
				 System.out.println(expected2);
				 System.out.println(result2);
				 
				 assertEquals(expected,result);
				  verify(this.tasksService, atLeastOnce()).removeTaskFromToDoList(testID);
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

}
