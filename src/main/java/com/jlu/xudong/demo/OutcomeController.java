package com.jlu.xudong.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jlu.xudong.demo.model.Outcome;
import com.jlu.xudong.demo.model.User;
import com.jlu.xudong.demo.repository.OutcomeRepository;
import com.jlu.xudong.demo.repository.UserRepository;
import com.jlu.xudong.demo.net.Response;

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

    // @GetMapping("/outcome")
    // public List<Outcome> outcome(int id) {
    // User user = userRepository.findById(id).get();
    // if (user == null)
    // return new ArrayList<>(0);
    // return outcomeRepository.findByUser(user);
    // }

    @GetMapping("/outcome")
    public Response<List<Outcome>> outcome(int id) {
        Response<List<Outcome>> response = new Response<>();
        User user = userRepository.findById(id).get();

        if (user == null) {
            response.body = new ArrayList<>(0);
            return response;
        }
        response.body = outcomeRepository.findByUser(user);
        return response;
    }

    @GetMapping("/saveOutCome")
    public Response<Outcome> save(int id, String type, double cash) {
        Response<Outcome> response = new Response<>();
        User user = userRepository.findById(id).get();

        if (user == null)
            return null;

        response.body = new Outcome();
        response.body.cash = cash;
        response.body.outcomeType = type;
        response.body.dateOutcome = new Date();
        response.body.user = user;

        response.body = outcomeRepository.save(response.body);

        return response;
    }
}