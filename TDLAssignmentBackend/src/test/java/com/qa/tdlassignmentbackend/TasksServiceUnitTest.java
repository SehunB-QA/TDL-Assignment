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

import com.qa.tdlassignmentbackend.dtos.TasksDTO;
import com.qa.tdlassignmentbackend.persistence.domain.Tasks;
import com.qa.tdlassignmentbackend.persistence.repo.TasksRepo;
import com.qa.tdlassignmentbackend.service.TaskService;

@SpringBootTest
public class TasksServiceUnitTest {

	@MockBean
	private TasksRepo tasksRepo;
	@MockBean
	private ModelMapper mockMapper;

	@Autowired
	private TaskService taskService;

	private final Tasks testTask = new Tasks(2L, "Buy Milk", "Semiskimmed", "Red");
	private final List<Tasks> fullTasks = List.of(testTask);
	private TasksDTO testTaskDTO;

	private static final ModelMapper testMapper = new ModelMapper();

	// private final List<ToDoList> fullLists = List.of(testToDoList);

	private TasksDTO mapToDTO(Tasks tasks) {
		return TasksServiceUnitTest.testMapper.map(tasks, TasksDTO.class);
	}

	@Test
	public void createTest() {

		TasksDTO testDTO = mapToDTO(testTask);

		Mockito.when(tasksRepo.save(testTask)).thenReturn(testTask);
		Mockito.when(mockMapper.map(testTask, TasksDTO.class)).thenReturn(testDTO);
		TasksDTO result = taskService.createTask(testTask);

		assertEquals(testDTO, result);

		Mockito.verify(this.tasksRepo, Mockito.times(1)).save(testTask);
	}

	@Test
	public void readTest() throws Exception {

		List<TasksDTO> testReadTaskList = fullTasks.stream().map(this::mapToDTO).collect(Collectors.toList());

		when(this.taskService.readAllTasks()).thenReturn(testReadTaskList);
		List<TasksDTO> result = testReadTaskList;
		List<Tasks> expected = this.tasksRepo.findAll();
		assertEquals(expected, result);

		Mockito.verify(this.tasksRepo, Mockito.times(1)).findAll();
	}

	@Test
	public void readByIDTest() {
		Long testID = 2L;

		testTaskDTO = mapToDTO(testTask);

		Optional<Tasks> list = Optional.of(this.testTask);
		Mockito.when(this.tasksRepo.findById(testID)).thenReturn(list);
		Mockito.when(mockMapper.map(testTask, TasksDTO.class)).thenReturn(testTaskDTO);
		TasksDTO result = this.taskService.readByID(testID);

		assertEquals(testTaskDTO, result);

		Mockito.verify(this.tasksRepo, Mockito.times(1)).findById(testID);
	}

	@Test
	public void readByNameTest() {
		String testName = "Buy Milk";

		testTaskDTO = mapToDTO(testTask);

		Optional<Tasks> task = Optional.of(this.testTask);
		Mockito.when(this.tasksRepo.findByName(testName)).thenReturn(task);
		Mockito.when(mockMapper.map(testTask, TasksDTO.class)).thenReturn(testTaskDTO);
		TasksDTO result = this.taskService.findByName(testName);

		assertEquals(testTaskDTO, result);

		Mockito.verify(this.tasksRepo, Mockito.times(1)).findByName(testName);
	}

	@Test
	public void readByColourTest() {
		String testColour = "Red";

		testTaskDTO = mapToDTO(testTask);

		Optional<Tasks> task = Optional.of(this.testTask);
		Mockito.when(this.tasksRepo.findByColourCode(testColour)).thenReturn(task);
		Mockito.when(mockMapper.map(testTask, TasksDTO.class)).thenReturn(testTaskDTO);
		TasksDTO result = this.taskService.findByColourCode(testColour);

		assertEquals(testTaskDTO, result);

		Mockito.verify(this.tasksRepo, Mockito.times(1)).findByColourCode(testColour);
	}

	@Test
	public void deleteTest() {

		Long testID = 2L;

		testTaskDTO = this.mapToDTO(testTask);
		TasksRepo delete = Mockito.mock(TasksRepo.class);
		Mockito.doNothing().when(delete).deleteById(testID);
		delete.deleteById(testID);
		Mockito.when(this.tasksRepo.existsById(testID)).thenReturn(true);
		boolean result = this.taskService.deleteTask(testID);
		boolean expected = !this.tasksRepo.existsById(testID);
		assertEquals(expected, result);

		Mockito.verify(this.tasksRepo, Mockito.times(1)).deleteById(testID);
	}

	@Test
	public void removeTaskFromListTest() {

		Long testID = 2L;

		testTaskDTO = this.mapToDTO(testTask);

		when(this.tasksRepo.removeTaskFromToDoList(testID)).thenReturn(1);

		Mockito.when(this.tasksRepo.existsById(testID)).thenReturn(true);
		boolean result = this.taskService.removeTaskFromToDoList(testID);
		boolean expected = this.tasksRepo.existsById(testID);
		assertEquals(expected, result);

		Mockito.verify(this.tasksRepo, Mockito.times(1)).removeTaskFromToDoList(testID);
	}

}
