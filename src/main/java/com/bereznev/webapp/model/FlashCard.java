package com.bereznev.webapp.model;
/*
    =====================================
    @project FlashCards
    @created 06/02/2023    
    @author Bereznev Nikita @CreativeWex
    =====================================
 */

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "cards")
public class FlashCard {

    @Getter
    @AllArgsConstructor
    public enum difficulties {
        EASY("EASY"),
        MEDIUM("MEDIUM"),
        HARD("HARD");

        private final String value;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;
    private String answer;
    private String difficulty;
    private String category;
}
