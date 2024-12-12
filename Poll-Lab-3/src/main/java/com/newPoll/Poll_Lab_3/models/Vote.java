package com.newPoll.Poll_Lab_3.models;

import jakarta.persistence.*;

@Entity
public class Vote {

    @Id
    @GeneratedValue
    @Column(name="VOTE_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name="OPTION_ID")
    private OptionModel option;


    public Vote() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OptionModel getOption() {
        return option;
    }

    public void setOption(OptionModel option) {
        this.option = option;
    }
}


