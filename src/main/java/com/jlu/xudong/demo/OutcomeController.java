package com.jlu.xudong.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jlu.xudong.demo.model.Outcome;
import com.jlu.xudong.demo.model.User;
import com.jlu.xudong.demo.repository.OutcomeRepository;
import com.jlu.xudong.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class OutcomeController {

    private final OutcomeRepository outcomeRepository;
    private final UserRepository userRepository;

    @Autowired
    public OutcomeController(OutcomeRepository outcomeRepository, UserRepository userRepository) {
        this.outcomeRepository = outcomeRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/outcome")
    public List<Outcome> outcome(int id) {
        User user = userRepository.findById(id).get();
        if (user == null)
            return new ArrayList<>(0);
        return outcomeRepository.findByUser(user);
    }

    @GetMapping("/saveOutCome")
    public Outcome save(int id, String type, double cash) {
        User user = userRepository.findById(id).get();
        if (user == null)
            return null;
        Outcome outcome = new Outcome();
        outcome.cash = cash;
        outcome.outcomeType = type;
        outcome.dateOutcome = new Date();
        outcome.user = user;

        return outcomeRepository.save(outcome);
    }
}