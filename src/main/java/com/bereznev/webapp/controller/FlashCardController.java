package com.bereznev.webapp.controller;
/*
    =====================================
    @project FlashCards
    @created 06/02/2023    
    @author Bereznev Nikita @CreativeWex
    =====================================
 */

import com.bereznev.webapp.model.FlashCard;
import com.bereznev.webapp.service.FlashCardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api/flash_cards")
public class FlashCardController {

    private final FlashCardService flashCardService;

    public FlashCardController(FlashCardService flashCardService) {
        super();
        this.flashCardService = flashCardService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<FlashCard> save(@RequestBody FlashCard flashCard) {
        return new ResponseEntity<FlashCard>(flashCardService.save(flashCard), HttpStatus.CREATED);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public String getAll(Model model) {
        model.addAttribute("flashCards", flashCardService.getAll());
        return "flashCards/getAll";
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public String getById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("card", flashCardService.getById(id));
        return "flashCards/getById";
    }

    @PutMapping("{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<FlashCard> update(@PathVariable("id") Long id, @RequestBody FlashCard flashCard) {
        return new ResponseEntity<FlashCard>(flashCardService.update(id, flashCard), HttpStatus.OK);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> delete(Long id) {
        flashCardService.delete(id);
        return new ResponseEntity<String>("Student deleted successfully!", HttpStatus.OK);
    }

    @GetMapping("random")
    @ResponseStatus(HttpStatus.OK)
    public String getRandom(@RequestParam(name = "cardsNumber") Optional<Integer> cardsNumber, Model model) {
        model.addAttribute("flashCards", flashCardService.getRandomNCards(cardsNumber.orElse(0)));
        model.addAttribute("maxCardsNumber", flashCardService.getAll().size());
        model.addAttribute("previousNumber", cardsNumber.orElse(0));
        return "flashCards/getRandomCards";
    }
}
