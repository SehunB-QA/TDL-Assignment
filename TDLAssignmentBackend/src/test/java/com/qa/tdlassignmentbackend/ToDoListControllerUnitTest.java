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

import com.qa.tdlassignmentbackend.controller.ToDoListController;
import com.qa.tdlassignmentbackend.dtos.ToDoListDTO;
import com.qa.tdlassignmentbackend.persistence.domain.ToDoList;
import com.qa.tdlassignmentbackend.service.ToDoListService;

@SpringBootTest
public class ToDoListControllerUnitTest {

	@Autowired
	private ToDoListController controller;

	@MockBean
	private ToDoListService toDoListService;

	@Autowired
	private ModelMapper mapper;

	private final ToDoList testToDoList = new ToDoList(2L, "Housekeeping");
	private final List<ToDoList> fullLists = List.of(testToDoList);

	private ToDoListDTO mapToDTO(ToDoList toDoList) {
		return this.mapper.map(toDoList, ToDoListDTO.class);
	}

	@Test
	public void createTest() throws Exception {
		// WHEN

		when(this.toDoListService.createList(testToDoList)).thenReturn(this.mapToDTO(testToDoList));

		// THIS
		assertThat(new ResponseEntity<ToDoListDTO>(this.mapToDTO(testToDoList), HttpStatus.CREATED))
				.isEqualTo(this.controller.create(testToDoList));

		// System.out.println("Expected:" + testToDoList);
		// System.out.println("Result:" + controller.create(testToDoList));
		verify(this.toDoListService, atLeastOnce()).createList(testToDoList);

	}

	@Test
	public void readTest() throws Exception {

		List<ToDoListDTO> testReadList = fullLists.stream().map(this::mapToDTO).collect(Collectors.toList());

		when(this.toDoListService.readAllLists()).thenReturn(testReadList);
		ResponseEntity<List<ToDoListDTO>> expected = ResponseEntity.ok(testReadList);
		ResponseEntity<List<ToDoListDTO>> result = this.controller.readAll();
		assertEquals(expected, result);
		// System.out.println("Expected:" + expected);
		// System.out.println("Result:" + result);

		verify(this.toDoListService, atLeastOnce()).readAllLists();
	}

	@Test
	public void readByIDTest() throws Exception {
		Long testID = 2L;
		ToDoListDTO testReadIDList = this.mapToDTO(testToDoList);

		when(this.toDoListService.readListByID(testID)).thenReturn(testReadIDList);
		ResponseEntity<ToDoListDTO> expected = ResponseEntity.ok(testReadIDList);
		ResponseEntity<ToDoListDTO> result = this.controller.readSingleList(testID);
		assertEquals(expected, result);

		verify(this.toDoListService, atLeastOnce()).readListByID(testID);
	}

	@Test
	public void findByNameTest() throws Exception {
		String testName = "housekeeping";
		ToDoListDTO testReadNameList = this.mapToDTO(testToDoList);
		when(this.toDoListService.findByName(testName)).thenReturn(testReadNameList);
		ResponseEntity<ToDoListDTO> expected = ResponseEntity.ok(testReadNameList);
		ResponseEntity<ToDoListDTO> result = this.controller.findByName(testName);
		assertEquals(expected, result);
		// System.out.println("Expected:" + expected);
		// System.out.println("Result:" + result);

		verify(this.toDoListService, atLeastOnce()).findByName(testName);
	}

	@Test
	public void updateTest() throws Exception {

		Long testID = 2L;
		ToDoListDTO testUpdateNameList = this.mapToDTO(testToDoList);
		ToDoListDTO newList = new ToDoListDTO();
		newList.setId(testID);
		newList.setName("Updated name");

		when(this.toDoListService.updateList(testUpdateNameList, testID)).thenReturn(newList);
		ResponseEntity<ToDoListDTO> expected = new ResponseEntity<>(newList, HttpStatus.ACCEPTED);
		ResponseEntity<ToDoListDTO> result = this.controller.update(testID, testUpdateNameList);

		assertEquals(expected, result);
		verify(this.toDoListService, atLeastOnce()).updateList(testUpdateNameList, testID);
	}

	@Test
	public void deleteTest() throws Exception {
		Long testID = 2L;
		when(this.toDoListService.deleteEntireToDoList(testID)).thenReturn(true);
		ResponseEntity<ToDoListDTO> expected = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		ResponseEntity<ToDoListDTO> result = this.controller.delete(testID);
		System.out.println(expected);
		System.out.println(result);

		assertEquals(expected, result);
		verify(this.toDoListService, atLeastOnce()).deleteEntireToDoList(testID);

		when(this.toDoListService.deleteEntireToDoList(testID)).thenReturn(false);
		ResponseEntity<ToDoListDTO> expected2 = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		ResponseEntity<ToDoListDTO> result2 = this.controller.delete(testID);
		System.out.println(expected2);
		System.out.println(result2);

		assertEquals(expected, result);
		verify(this.toDoListService, atLeastOnce()).deleteEntireToDoList(testID);

	}

}
