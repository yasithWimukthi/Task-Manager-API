package com.threerive.TaskManager.service;

import com.threerive.TaskManager.dto.TaskRequest;
import com.threerive.TaskManager.model.Task;
import com.threerive.TaskManager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
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
        Task task = new Task();
        task.setName(taskRequest.getName());
        task.setDescription(taskRequest.getDescription());
        task.setPriority(taskRequest.getPriority());
        task.setStatus(taskRequest.getStatus());
        // Additional validation logic if needed
        return (Task) taskRepository.save(task);
    }

    /**
     * Update an existing task
     * @param id
     * @param updatedTask
     * @return Task
     */
    public Task updateTask(Long id, Task updatedTask) {
        // Additional validation and error handling if needed
        Task existingTask = (Task) taskRepository.findById(id).orElse(null);
        if (existingTask != null) {
            existingTask.setName(updatedTask.getName());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setPriority(updatedTask.getPriority());
            existingTask.setStatus(updatedTask.getStatus());
            return (Task) taskRepository.save(existingTask);
        }
        return null;
    }
}
