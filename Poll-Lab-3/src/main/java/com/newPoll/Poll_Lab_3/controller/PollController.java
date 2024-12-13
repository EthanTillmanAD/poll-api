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
    PollRepository pollRepository;
    @Autowired
    PollService pollService;

    protected void verifyPoll(Long pollId) throws ResoureceNotFoundException{
        Poll myPoll = null;

        if (pollRepository.findById(pollId).isPresent()){
            Poll poll = pollRepository.findById(pollId).get();
           myPoll = poll;
            logger.info("poll verified");
        }

        if (myPoll == null){
             throw new ResoureceNotFoundException("Poll with id " + pollId + " not found");
        }

    }


    @RequestMapping(value="/polls", method=RequestMethod.GET)
    public ResponseEntity<Iterable<Poll>> getAllPolls() {
        Iterable<Poll> allPolls = pollRepository.findAll();
        logger.info("polls listed");
        return new ResponseEntity<>(pollRepository.findAll(), HttpStatus.OK);

    }

    @RequestMapping(value="/polls", method=RequestMethod.POST)
    public ResponseEntity<?> createPoll(@Valid @RequestBody Poll poll) {
        poll = pollRepository.save(poll);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(poll.getId()).toUri();
        responseHeaders.setLocation(newPollUri);
        logger.info("Poll created");
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value="/polls/{pollId}", method=RequestMethod.GET)
    public ResponseEntity<?> getPoll(@PathVariable Long pollId) {
        Poll myPoll = null;
        if (pollRepository.findById(pollId).isPresent()) {
            Poll p = pollRepository.findById(pollId).get();
            myPoll = p;
        }
            if (myPoll == null) {
                throw new ResoureceNotFoundException("Poll with id " + pollId + " not found");

            }
                logger.info("getting poll #"+ pollId);
        return new ResponseEntity<> (myPoll, HttpStatus.OK);
    }


    @RequestMapping(value="/polls/{pollId}", method=RequestMethod.PUT)
    public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable Long pollId) {


        verifyPoll(pollId);
        pollService.changePoll(poll, pollId);

        logger.info("poll updated" + poll);

        return new ResponseEntity<>(poll,HttpStatus.ACCEPTED);
    }
    @RequestMapping(value="/polls/{pollId}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deletePoll(@PathVariable Long pollId) {
        verifyPoll(pollId);

        pollRepository.deleteById(pollId);

        logger.info("poll deleted");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
