package com.threerive.TaskManager.service;

import com.threerive.TaskManager.model.Task;
import com.threerive.TaskManager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Create a new task
     * @param task
     * @return Task
     */
    public Task createTask(Task task) {
        // Additional validation logic if needed
        return (Task) taskRepository.save(task);
    }
}
