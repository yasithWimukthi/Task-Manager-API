package com.threerive.TaskManager.dto;

import com.threerive.TaskManager.enums.TaskPriority;
import com.threerive.TaskManager.enums.TaskStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Getter
public class TaskRequest {

    @NotNull(message = "Name cannot be empty.")
    private String name;

    @NotNull(message = "Description cannot be empty.")
    private String description;

    @NotNull(message = "Priority cannot be empty.")
    private TaskPriority priority;

    @NotNull(message = "Status cannot be empty.")
    private TaskStatus status;
}
