package com.newPoll.Poll_Lab_3.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.BatchSize;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.lang.NonNull;

import java.lang.Throwable.*;

import java.util.Set;

@Entity
public class Poll {

    @Id
    @GeneratedValue
    @Column(name="POLL_ID")
    private Long id;


    @Column(name="QUESTION")
    @NotEmpty
    private String question;



    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "POLL_ID")
    @OrderBy
    @Size(min = 2, max = 6)
    @NotEmpty
    public Set<OptionModel> options;


    public Poll() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Set<OptionModel> getOptions() {
        return options;
    }

    public void setOptions(Set<OptionModel> options) {
        this.options = options;
    }
}
