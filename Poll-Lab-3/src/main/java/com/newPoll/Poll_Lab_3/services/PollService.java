package com.newPoll.Poll_Lab_3.services;

import com.newPoll.Poll_Lab_3.models.Poll;
import com.newPoll.Poll_Lab_3.repositories.PollRepository;
import com.newPoll.Poll_Lab_3.repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PollService {

    @Autowired
    PollRepository pollRepository;

    public Iterable<Poll> allPolls(){
        return pollRepository.findAll();
    }



    public Poll changePoll(Poll poll, Long id){
        for(Poll p : allPolls()){
            if (p.getId().equals(id)){
                p.setQuestion(poll.getQuestion());
                p.setOptions(poll.getOptions());
                return pollRepository.save(p);
            }
        }
        return null;

    }


}
