package com.bereznev.webapp.model;
/*
    =====================================
    @project FlashCards
    @created 21/02/2023    
    @author Bereznev Nikita @CreativeWex
    =====================================
 */

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "tasks")
public class SubTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 2, max = 100, message = "Имя должно быть не меньше 2 и не больше 100 символов!")
    private String name;
    private Date date;
    private String description;

    @ManyToOne
    private Task task;
}
