package com.bereznev.webapp.model;
/*
    =====================================
    @project FlashCards
    @created 21/02/2023    
    @author Bereznev Nikita @CreativeWex
    =====================================
 */

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 2, max = 100, message = "Имя должно быть не меньше 2 и не больше 100 символов!")
    private String name;
    private Date date;
    private String description;
    @Pattern(regexp = "(FINISHED)|(ACTIVE)")
    private String status;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubTask> subTasks = new ArrayList<>();

    public void addSubtask(SubTask subTask) {
        subTasks.add(subTask);
        subTask.setTask(this);
    }

    public void removeSubTask(SubTask subTask) {
        subTasks.remove(subTask);
        subTask.setTask(null);
    }
}
