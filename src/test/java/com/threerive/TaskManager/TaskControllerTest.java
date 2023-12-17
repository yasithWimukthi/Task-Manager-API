package com.threerive.TaskManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.threerive.TaskManager.controller.TaskController;
import com.threerive.TaskManager.dto.TaskRequest;
import com.threerive.TaskManager.model.Task;
import com.threerive.TaskManager.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @Mock
    private Validator validator;

    @InjectMocks
    private TaskController taskController;

    @Test
    void getAllTasks() {
        // Mock the behavior of the service
        when(taskService.getAllTasks()).thenReturn(Arrays.asList(new Task(), new Task()));

        // Call the controller method
        List<Task> tasks = taskController.getAllTasks();

        // Verify the result
        assertNotNull(tasks);
        assertEquals(2, tasks.size());
    }

    @Test
    void getTaskById() {
        Long taskId = 1L;
        Task task = new Task();
        when(taskService.getTaskById(taskId)).thenReturn(Optional.of(task));

        // Call the controller method
        ResponseEntity<?> responseEntity = taskController.getTaskById(taskId);

        // Verify the result
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() instanceof Task);
    }

    @Test
    void createTask() {
        TaskRequest taskRequest = new TaskRequest();
        Errors errors = new BeanPropertyBindingResult(taskRequest, "taskRequest");
        when(validator.supports(eq(TaskRequest.class))).thenReturn(true);
        doNothing().when(validator).validate(eq(taskRequest), any(Errors.class));

        // Mock the behavior of the service
        when(taskService.createTask(taskRequest)).thenReturn(new Task());

        // Call the controller method
        ResponseEntity<Object> responseEntity = taskController.createTask(taskRequest);

        // Verify the result
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() instanceof Task);
    }

    @Test
    void updateTask() {
        Long taskId = 1L;
        TaskRequest updatedTask = new TaskRequest();
        Errors errors = new BeanPropertyBindingResult(updatedTask, "updatedTask");
        when(validator.supports(eq(TaskRequest.class))).thenReturn(true);
        doNothing().when(validator).validate(eq(updatedTask), any(Errors.class));

        // Mock the behavior of the service
        when(taskService.updateTask(taskId, updatedTask)).thenReturn(new Task());

        // Call the controller method
        ResponseEntity<Object> responseEntity = taskController.updateTask(taskId, updatedTask);

        // Verify the result
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() instanceof Task);
    }

    @Test
    void deleteTask() {
        Long taskId = 1L;

        // Call the controller method
        ResponseEntity<Object> responseEntity = taskController.deleteTask(taskId);

        // Verify the result
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
