package com.qa.tdlassignmentbackend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.qa.tdlassignmentbackend.dtos.ToDoListDTO;
import com.qa.tdlassignmentbackend.persistence.domain.ToDoList;
import com.qa.tdlassignmentbackend.persistence.repo.ToDoListRepo;
import com.qa.tdlassignmentbackend.service.ToDoListService;

@SpringBootTest
public class ToDoListServiceUnitTest {
	
	
	@MockBean
	private ToDoListRepo toDoListRepo;
	@MockBean
	private ModelMapper mockMapper;
    
	@Autowired
    private ToDoListService toDoListService;
 
	  private final ToDoList testToDoList = new ToDoList(2L, "Housekeeping");
	   private final List<ToDoList> fullLists = List.of(testToDoList);
	private ToDoListDTO  testListDTO;
	
    
	
    private static final ModelMapper testMapper = new ModelMapper();
	
	
   
    //private final List<ToDoList> fullLists = List.of(testToDoList);
    
    private ToDoListDTO mapToDTO(ToDoList toDoList)
    {
    	return ToDoListServiceUnitTest.testMapper.map(toDoList, ToDoListDTO.class);
    }
    
  
    @Test
    public void createTest()
    {
    	
  
    ToDoListDTO testDTO = mapToDTO(testToDoList);
    
  Mockito.when(toDoListRepo.save(testToDoList)).thenReturn(testToDoList);
  Mockito.when(mockMapper.map(testToDoList, ToDoListDTO.class) ).thenReturn(testDTO);   
    ToDoListDTO result = toDoListService.createList(testToDoList);
    
    assertEquals(testDTO,result);
    
    Mockito.verify(this.toDoListRepo, Mockito.times(1)).save(testToDoList);
    }
	
    

	@Test 
	public void readTest() throws Exception
    {
		
		List<ToDoListDTO> testReadList =  fullLists.stream().map(this:: mapToDTO).
				collect(Collectors.toList());
				
		when(this.toDoListService.readAllLists()).thenReturn(testReadList);
		List<ToDoListDTO> result = testReadList;
		List<ToDoList> expected = this.toDoListRepo.findAll();
		assertEquals(expected, result);
		

		Mockito.verify(this.toDoListRepo, Mockito.times(1)).findAll();
    }
	
	
	
	     @Test
	    public void readByIDTest()
	    {
	    	Long testID= 2L;
	  
	    	 testListDTO = mapToDTO(testToDoList);
			
	   Optional<ToDoList> list = Optional.of(this.testToDoList);
	  Mockito.when(this.toDoListRepo.findById(testID)).thenReturn(list);
	  Mockito.when(mockMapper.map(testToDoList, ToDoListDTO.class) ).thenReturn(testListDTO);
	    ToDoListDTO result = this.toDoListService.readListByID(testID);
	   
	    
	    assertEquals(testListDTO,result);
	    
	    Mockito.verify(this.toDoListRepo, Mockito.times(1)).findById(testID);
	    }
		
	
	     
	     @Test
		    public void readByNameTest()
		    {
		    	String testName = "Housekeeping";
		  
		    	 testListDTO = mapToDTO(testToDoList);
				
		   Optional<ToDoList> list = Optional.of(this.testToDoList);
		  Mockito.when(this.toDoListRepo.findByName(testName)).thenReturn(list);
		  Mockito.when(mockMapper.map(testToDoList, ToDoListDTO.class) ).thenReturn(testListDTO);
		    ToDoListDTO result = this.toDoListService.findByName(testName);
		   
		    
		    assertEquals(testListDTO,result);
		    
		    Mockito.verify(this.toDoListRepo, Mockito.times(1)).findByName(testName);
		    }
	
	
		    
	     @Test
	     public void deleteTest()
		    {

		    	Long testID= 2L;
		  
		    	 testListDTO = this.mapToDTO(testToDoList);
		    	ToDoListRepo delete = Mockito.mock(ToDoListRepo.class);
		    	 Mockito.doNothing().when(delete).deleteById(testID);
		    	 delete.deleteById(testID);
		    	 Mockito.when(this.toDoListRepo.existsById(testID)).thenReturn(true);
                boolean result = this.toDoListService.deleteEntireToDoList(testID);
		    	boolean expected = !this.toDoListRepo.existsById(testID);
		    	 assertEquals(expected,result);
		    	 
		    	 
		    Mockito.verify(this.toDoListRepo, Mockito.times(1)).deleteById(testID);
		    }
}
