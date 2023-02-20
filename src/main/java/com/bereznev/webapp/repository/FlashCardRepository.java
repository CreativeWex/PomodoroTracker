package com.bereznev.webapp.repository;
/*
    =====================================
    @project FlashCards
    @created 06/02/2023    
    @author Bereznev Nikita @CreativeWex
    =====================================
 */

import com.bereznev.webapp.model.FlashCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlashCardRepository extends JpaRepository<FlashCard, Long> {}
