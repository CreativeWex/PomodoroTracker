package com.bereznev.studentrecords.model;
/*
    =====================================
    @project StudentRecords
    @created 06/02/2023    
    @author Bereznev Nikita @CreativeWex
    =====================================
 */

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    @NotEmpty(message = "wrong first name")
    @Size(min = 2, max = 50, message = "Parameter's length must be between 2 and 50 symbols")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotEmpty(message = "wrong last name")
    private String lastName;

    @Column(name = "email", nullable = false)
    @NotEmpty(message = "Wrong email")
    @Email(message = "Wrong email")
    private String email;
}
