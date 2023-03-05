package com.bereznev.webapp.service;
/*
    =====================================
    @project FlashCards
    @created 06/02/2023    
    @author Bereznev Nikita @CreativeWex
    =====================================
 */

import com.bereznev.webapp.exception.ResourceNotFoundException;
import com.bereznev.webapp.model.Task;
import com.bereznev.webapp.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private static boolean SORTED = true;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> getAll(String sortParameter) {
        SORTED = !SORTED;
        if (SORTED) {
            return taskRepository.findAll(Sort.by(Sort.Direction.DESC, sortParameter));
        }
        return taskRepository.findAll(Sort.by(Sort.Direction.ASC, sortParameter));
    }

    @Override
    public Task getById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task", "Id", id));
    }

    @Override
    public Task update(Long id, Task updatedTask) {
        Task existedTask = taskRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Task", "Id", id));

        existedTask.setName(updatedTask.getName());
        existedTask.setDate(updatedTask.getDate());
        existedTask.setDescription(updatedTask.getDescription());

        taskRepository.save(existedTask);
        return existedTask;
    }

    @Override
    public void delete(Long id) {
        taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task", "Id", id));
        taskRepository.deleteById(id);
    }

    @Override
    public void changeStatus(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Task", "Id", id));

        switch (task.getStatus()) {
            case ("ACTIVE") -> task.setStatus("FINISHED");
            case ("FINISHED") -> task.setStatus("ACTIVE");
        }
        taskRepository.save(task);
    }

    @Override
    public List<Task> getActive() {
        return null;
    }

    @Override
    public List<Task> getFinished() {
        return null;
    }
}
