package com.threerive.TaskManager.dto;

import com.threerive.TaskManager.enums.TaskPriority;
import com.threerive.TaskManager.enums.TaskStatus;
import com.threerive.TaskManager.validator.EmptyStringToNullDeserializer;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Getter
public class TaskRequest {

    @NotNull(message = "Name cannot be empty.")
    @JsonDeserialize(using = EmptyStringToNullDeserializer.class)
    private String name;

    @NotNull(message = "Description cannot be empty.")
    @JsonDeserialize(using = EmptyStringToNullDeserializer.class)
    private String description;

    @NotNull(message = "Priority cannot be empty.")
    @JsonDeserialize(using = EmptyStringToNullDeserializer.class)
    private TaskPriority priority;

    @NotNull(message = "Status cannot be empty.")
    @JsonDeserialize(using = EmptyStringToNullDeserializer.class)
    private TaskStatus status;
}
