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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        super();
        this.taskService = taskService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Task> save(@RequestBody Task task) {
        return new ResponseEntity<Task>(taskService.save(task), HttpStatus.CREATED);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public String getAll(Model model) {
        model.addAttribute("tasksList", taskService.getAll());
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
