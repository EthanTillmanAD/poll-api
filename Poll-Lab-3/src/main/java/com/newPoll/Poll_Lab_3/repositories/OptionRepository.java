package com.newPoll.Poll_Lab_3.repositories;


import com.newPoll.Poll_Lab_3.models.OptionModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends CrudRepository<OptionModel, Long> {
}
