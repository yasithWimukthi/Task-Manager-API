package com.threerive.TaskManager.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.threerive.TaskManager.model.Task;

public interface TaskRepository<Task> extends JpaRepository<com.threerive.TaskManager.model.Task, Long> {

}