package com.bereznev.webapp.service;
/*
    =====================================
    @project StudentRecords
    @created 06/02/2023    
    @author Bereznev Nikita @CreativeWex
    =====================================
 */

import com.bereznev.webapp.model.FlashCard;

import java.util.List;

public interface FlashCardService {
    // CRUD
    FlashCard save(FlashCard flashCard);
    List<FlashCard> getAll();
    FlashCard getById(Long id);
    FlashCard update(Long id, FlashCard updatedFlashCard);
    void delete(Long id);

    // Business logic
    List<FlashCard> getRandomNCards(int number);
    FlashCard increaseDifficulty(Long id);
    FlashCard decreaseDifficulty(Long id);
}
