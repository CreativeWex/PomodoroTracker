package com.bereznev.webapp.service;
/*
    =====================================
    @project FlashCards
    @created 06/02/2023    
    @author Bereznev Nikita @CreativeWex
    =====================================
 */

import com.bereznev.webapp.exception.ResourceNotFoundException;
import com.bereznev.webapp.exception.TooBigArgumentException;
import com.bereznev.webapp.model.FlashCard;
import com.bereznev.webapp.repository.FlashCardRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class FlashCardServiceImpl implements FlashCardService {

    private final FlashCardRepository flashCardRepository;

    @Autowired
    public FlashCardServiceImpl(FlashCardRepository flashCardRepository) {
        this.flashCardRepository = flashCardRepository;
    }

    @Override
    public FlashCard save(FlashCard flashCard) {
        return flashCardRepository.save(flashCard);
    }

    @Override
    public List<FlashCard> getAll() {
        return flashCardRepository.findAll();
    }

    @Override
    public FlashCard getById(Long id) {
        return flashCardRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student", "Id", id));
    }

    @Override
    public FlashCard update(Long id, FlashCard updatedFlashCard) {
        FlashCard existedFlashCard = flashCardRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("FlashCard", "Id", id));

        existedFlashCard.setQuestion(updatedFlashCard.getQuestion());
        existedFlashCard.setAnswer(updatedFlashCard.getAnswer());
        existedFlashCard.setDifficulty(updatedFlashCard.getDifficulty());
        existedFlashCard.setCategory(updatedFlashCard.getCategory());

        flashCardRepository.save(existedFlashCard);
        return existedFlashCard;
    }

    @Override
    public void delete(Long id) {
        flashCardRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("FlashCard", "Id", id));
        flashCardRepository.deleteById(id);
    }

    @Override
    public List<FlashCard> getRandomNCards(int number) {
        List<FlashCard> allCards = getAll();
        List<FlashCard> randomCards = new ArrayList<>();

        if (number > allCards.size()) {
            throw new TooBigArgumentException("getRandomNCards", "number", number);
        }
        for (int i = 0; i < number; i++) {
            int index = new Random().nextInt(allCards.size());
            randomCards.add(allCards.get(index));
            allCards.remove(index);
        }
        return randomCards;
    }

    @Override
    public FlashCard increaseDifficulty(Long id) {
        FlashCard card = flashCardRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("FlashCard", "Id", id));
        switch (card.getDifficulty()) {
            case ("EASY") -> card.setDifficulty("MEDIUM");
            case ("MEDIUM") -> card.setDifficulty("HARD");
        }

        flashCardRepository.save(card);
        return card;
    }

    @Override
    public FlashCard decreaseDifficulty(Long id) {
        FlashCard card = flashCardRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("FlashCard", "Id", id));
        switch (card.getDifficulty()) {
            case ("HARD") -> card.setDifficulty("MEDIUM");
            case ("MEDIUM") -> card.setDifficulty("EASY");
        }

        flashCardRepository.save(card);
        return card;
    }

}
