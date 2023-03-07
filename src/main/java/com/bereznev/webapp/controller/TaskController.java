package com.bereznev.webapp.controller;
/*
    =====================================
    @project FlashCards
    @created 06/02/2023    
    @author Bereznev Nikita @CreativeWex
    =====================================
 */

import com.bereznev.webapp.model.Task;
import com.bereznev.webapp.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        super();
        this.taskService = taskService;
    }

    @PostMapping()
    public String save(@ModelAttribute("task") @Valid Task task, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            taskService.save(task);
        }
        return "redirect:/api/tasks";
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public String getAll(@RequestParam(name = "sort", required = false) Optional<String> sortParameter, @ModelAttribute("task") Task task, Model model) {
        if (sortParameter.isEmpty()) {
            System.out.println("Active tasks");
            List<Task> activeTasks = taskService.findActiveTasks();
            for (Task task1 : activeTasks) {
                System.out.println(task1);
            }
            model.addAttribute("activeTasks", taskService.findActiveTasks());
        }
        model.addAttribute("finishedTasks", taskService.findFinishedTasks());
        System.out.println("Finished tasks");
        List<Task> activeTasks = taskService.findFinishedTasks();
        for (Task task1 : activeTasks) {
            System.out.println(task1);
        }
        return "tasks/getAll";
    }

    @PatchMapping("{id}/change_status")
    public String changeStatus(@PathVariable("id") Long id) {
        taskService.changeStatus(id);
        return "redirect:/api/tasks";
    }


//    @GetMapping("{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public String getById(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("card", flashCardService.getById(id));
//        return "flashCards/getById";
//    }
//
//    @PutMapping("{id}/update")
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<FlashCard> update(@PathVariable("id") Long id, @RequestBody FlashCard flashCard) {
//        return new ResponseEntity<FlashCard>(flashCardService.update(id, flashCard), HttpStatus.OK);
//    }
//
    @DeleteMapping("{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        taskService.delete(id);
        return "redirect:/api/tasks";
    }

}
