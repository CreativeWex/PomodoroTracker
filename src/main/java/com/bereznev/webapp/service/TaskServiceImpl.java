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
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    @PersistenceContext
    private EntityManager entityManager;

    private final TaskRepository taskRepository;

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
    @SuppressWarnings("unchecked")
    public List<Task> findFinishedTasks() {
        if (entityManager == null || entityManager.unwrap(Session.class) == null) {
            throw new NullPointerException();
        }
        return entityManager.createQuery("select task from Task task where task.status = ?1").setParameter(1, "FINISHED").getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Task> findActiveTasks() {
        if (entityManager == null || entityManager.unwrap(Session.class) == null) {
            throw new NullPointerException();
        }
        return entityManager.createQuery("select task from Task task where task.status = ?1").setParameter(1, "ACTIVE").getResultList();
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

}
