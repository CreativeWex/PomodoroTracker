package com.bereznev.webapp.model;
/*
    =====================================
    @project FlashCards
    @created 21/02/2023    
    @author Bereznev Nikita @CreativeWex
    =====================================
 */

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
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
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 100, message = "Имя должно быть не меньше 2 и не больше 100 символов!")
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "description")
    private String description;

    @Column(name = "is_active", nullable = false, insertable = false, columnDefinition = "ACTIVE")
    private boolean isActive;
}
