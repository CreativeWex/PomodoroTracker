package com.bereznev.webapp.util;
/*
    =====================================
    @project TaskManager
    @created 08/03/2023    
    @author Bereznev Nikita @CreativeWex
    =====================================
 */

import com.bereznev.webapp.model.Task;
import com.bereznev.webapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class TaskValidator implements Validator {
    private final TaskService taskService;

    @Autowired
    public TaskValidator(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Task.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Task task = (Task) target;
        if (taskService.isAlreadyPresent(task.getId(), task.getName())) {
            errors.rejectValue("name", "", "Такая задача уже существует!");
        }
    }
}
