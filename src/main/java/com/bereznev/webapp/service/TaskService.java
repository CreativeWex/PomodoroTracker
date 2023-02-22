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

public interface TaskService {
    // CRUD
    Task save(Task task);
    List<Task> getAll();
    Task getById(Long id);
    Task update(Long id, Task updatedTask);
    void delete(Long id);

    // Business-logic
    public void changeStatus(Long id);
}
