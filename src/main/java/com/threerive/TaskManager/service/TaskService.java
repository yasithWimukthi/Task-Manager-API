package com.threerive.TaskManager.service;

import com.threerive.TaskManager.dto.TaskRequest;
import com.threerive.TaskManager.exception.TaskDeleteException;
import com.threerive.TaskManager.exception.TaskNotFoundException;
import com.threerive.TaskManager.exception.TaskUpdateException;
import com.threerive.TaskManager.model.Task;
import com.threerive.TaskManager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Get all tasks
     * @return List<Task>
     */
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    /**
     * Get task by id
     * @param id
     * @return Optional<Task>
     */
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }


    /**
     * Create a new task
     * @param taskRequest
     * @return Task
     */
    public Task createTask(TaskRequest taskRequest) {
        try {
            Task task = new Task();
            task.setName(taskRequest.getName());
            task.setDescription(taskRequest.getDescription());
            task.setPriority(taskRequest.getPriority());
            task.setStatus(taskRequest.getStatus());
            // Additional validation logic if needed
            return (Task) taskRepository.save(task);
        } catch (Exception e) {
            // Handle other unexpected exceptions
            logger.error("An error occurred: (Create Task) ", e.getMessage(), e);
            throw new TaskUpdateException(e.getMessage());
        }

    }

    /**
     * Update an existing task
     * @param id
     * @param updatedTask
     * @return Task
     */
    public Task updateTask(Long id, TaskRequest updatedTask) {
        // Additional validation and error handling if needed
        try {
            Task existingTask = (Task) taskRepository.findById(id).orElse(null);

            if (existingTask != null) {
                existingTask.setName(updatedTask.getName());
                existingTask.setDescription(updatedTask.getDescription());
                existingTask.setPriority(updatedTask.getPriority());
                existingTask.setStatus(updatedTask.getStatus());
                return (Task) taskRepository.save(existingTask);
            }else {
                // Task not found, you can throw a custom exception or handle it as needed
                throw new TaskNotFoundException("Task with id " + id + " not found");
            }
        }catch (Exception e) {
            // Handle other unexpected exceptions
            logger.error("An error occurred: (Update Task) ", e.getMessage(), e);
            throw new TaskUpdateException(e.getMessage());
        }
    }

    /**
     * Delete a task
     * @param id
     */
    public void deleteTask(Long id) {
        try {
            Optional<Task> existingTask = taskRepository.findById(id);

            if (existingTask.isPresent()) {
                taskRepository.deleteById(id);
            } else {
                // Task not found, you can throw a custom exception or handle it as needed
                throw new TaskNotFoundException("Task with id " + id + " not found");
            }
        } catch (EmptyResultDataAccessException e) {
            // Handle the case where the task was not found during deletion
            throw new TaskNotFoundException("Task with id " + id + " not found");
        } catch (Exception e) {
            // Handle other unexpected exceptions
            logger.error("An error occurred: (Delete Task) ", e.getMessage(), e);
            throw new TaskDeleteException(e.getMessage());
        }
    }

}
