package com.newPoll.Poll_Lab_3.controller;


import com.newPoll.Poll_Lab_3.exceptions.ResoureceNotFoundException;
import com.newPoll.Poll_Lab_3.models.Poll;
import com.newPoll.Poll_Lab_3.repositories.PollRepository;
import com.newPoll.Poll_Lab_3.services.PollService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class PollController {
    private final Logger logger = LoggerFactory.getLogger(PollController.class);

    @Autowired
    PollService pollService;

    @GetMapping("/polls")
    public ResponseEntity<?> getAllPolls() {
        logger.info("polls listed");
        return pollService.allPolls();
    }

    @PostMapping("/polls")
    public ResponseEntity<?> createPoll(@Valid @RequestBody Poll poll) {
        logger.info("Poll created");
        return pollService.addAPoll(poll);
    }

    @GetMapping("/polls/{pollId}")
    public ResponseEntity<?> getPoll(@PathVariable Long pollId) {
        logger.info("getting poll #"+ pollId);
        return pollService.getPollById(pollId);
    }


    @PutMapping("/polls/{pollId}")
    public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable Long pollId) {
        logger.info("poll updated" + poll);
        return new ResponseEntity<>(pollService.changePoll(poll, pollId),HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/polls/{pollId}")
    public ResponseEntity<?> deletePoll(@PathVariable Long pollId) {

        logger.info("poll deleted");
        return pollService.removePoll(pollId);
    }



}
