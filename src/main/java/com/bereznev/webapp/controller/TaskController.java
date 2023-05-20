package com.bereznev.webapp.controller;
/*
    =====================================
    @project TaskManager
    @created 06/02/2023    
    @author Bereznev Nikita @CreativeWex
    =====================================
 */

import com.bereznev.webapp.model.Task;
import com.bereznev.webapp.service.TaskService;
import com.bereznev.webapp.util.TaskValidator;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Log4j

@Controller
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskValidator taskValidator;

    public TaskController(TaskService taskService, TaskValidator taskValidator) {
        super();
        this.taskService = taskService;
        this.taskValidator = taskValidator;
    }

    @GetMapping("/new")
    public String addNewTask(@ModelAttribute("task") Task task, Model model) {
        log.debug("GET /new");
        return "tasks/new";
    }

    @PostMapping
    public String addNewTask(@ModelAttribute("task") @Valid Task task, BindingResult bindingResult) {
        taskValidator.validate(task, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/tasks/new";
        }
        taskService.save(task);
        log.debug("task saved:" + task);
        return "redirect:/api/v1/tasks";
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public String getAll(@ModelAttribute("task") Task task, Model model) {
        model.addAttribute("activeTasks", taskService.findActiveTasks());
        model.addAttribute("finishedTasks", taskService.findFinishedTasks());
        return "tasks/getAll";
    }

    @PatchMapping("/{id}/switch_active_status")
    public String switchActiveStatus(@PathVariable("id") Long id) {
        taskService.switchActiveStatus(id);
        return "redirect:/api/v1/tasks";
    }

    @PatchMapping("/{id}/switch_important_status")
    public String makeImportant(@PathVariable("id") Long id) {
        taskService.switchImportantStatus(id);
        return "redirect:/api/v1/tasks";
    }

    @PatchMapping("/{id}/update")
    public String update(@PathVariable("id") Long id, @ModelAttribute("updatedTask") @Valid Task task, BindingResult bindingResult, Model model) {
        taskValidator.validate(task, bindingResult);
        if (!bindingResult.hasErrors()) {
            taskService.save(task);
        }
        taskService.update(id, task);
        log.debug("updated task with id:" + id);
        return "redirect:/api/v1/tasks";
    }

    @DeleteMapping("{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        taskService.delete(id);
        log.debug("deleted task with id: " + id);
        return "redirect:/api/v1/tasks";
    }

}
