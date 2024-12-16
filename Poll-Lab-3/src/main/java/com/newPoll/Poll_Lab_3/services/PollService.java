package com.newPoll.Poll_Lab_3.services;

import com.newPoll.Poll_Lab_3.exceptions.ResoureceNotFoundException;
import com.newPoll.Poll_Lab_3.models.Poll;
import com.newPoll.Poll_Lab_3.repositories.PollRepository;
import com.newPoll.Poll_Lab_3.repositories.VoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Service
public class PollService {
private final Logger logger = LoggerFactory.getLogger(PollService.class);
    @Autowired
    PollRepository pollRepository;

    public ResponseEntity<?> allPolls(){

        return new ResponseEntity<>(pollRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> changePoll(Poll poll, Long id){
       verifyPoll(id);

        if (pollRepository.findById(id).isPresent()){
           Poll newPoll = pollRepository.findById(id).get();
           newPoll.setOptions(poll.getOptions());
           newPoll.setQuestion(poll.getQuestion());
          return new ResponseEntity<>(pollRepository.save(newPoll), HttpStatus.ACCEPTED);
       }

        return null;

    }

    public ResponseEntity<?> addAPoll(Poll poll){
        HttpHeaders headers = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(poll.getId()).toUri();
        headers.setLocation(newPollUri);
        return new ResponseEntity<>(pollRepository.save(poll), headers, HttpStatus.CREATED);
    }


    public ResponseEntity<?> getPollById(Long id){
        verifyPoll(id);

        Poll myPoll = null;
        if (pollRepository.findById(id).isPresent()) {
            Poll p = pollRepository.findById(id).get();
            myPoll = p;
        }
        if (myPoll == null) {
            throw new ResoureceNotFoundException("Poll with id " + id + " not found");

        }

        return new ResponseEntity<>(myPoll, HttpStatus.FOUND);
    }

    public ResponseEntity<?> removePoll(Long id){
        verifyPoll(id);
        pollRepository.deleteById(id);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }



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
}
