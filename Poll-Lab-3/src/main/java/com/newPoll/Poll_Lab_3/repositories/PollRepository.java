package com.newPoll.Poll_Lab_3.repositories;

import com.newPoll.Poll_Lab_3.models.Poll;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollRepository extends CrudRepository<Poll, Long> {
}
