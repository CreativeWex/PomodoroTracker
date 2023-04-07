package com.bereznev.webapp.service;
/*
    =====================================
    @project StudentRecords
    @created 06/02/2023    
    @author Bereznev Nikita @CreativeWex
    =====================================
 */

import com.bereznev.webapp.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    // CRUD
    List<Task> findActiveTasks();
    List<Task> findFinishedTasks();

    Task findById(Long id);
    Task save(Task task);
    Task update(Long id, Task updatedTask);
    void delete(Long id);

    // Business-logic
    public void switchActiveStatus(Long id);
    public void switchImportantStatus(Long id);

    // Validation
   public boolean isAlreadyPresent(Long id, String name);
}
