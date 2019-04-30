package com.jlu.xudong.demo;

import com.jlu.xudong.demo.model.Income;
import com.jlu.xudong.demo.model.User;
import com.jlu.xudong.demo.repository.IncomeRepository;
import com.jlu.xudong.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

@RestController

public class IncomeController {

    private final IncomeRepository incomeRepository;
    private final UserRepository userRepository;

    @Autowired
    public IncomeController(IncomeRepository incomeRepository, UserRepository userRepository) {
        this.incomeRepository = incomeRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/income")
    public List<Income> income(int id) {
        User user = userRepository.findById(id).get();
        if (user == null)
            return new ArrayList<>(0);
        return incomeRepository.findByUser(user);
    }

    @GetMapping("/saveIncome")
    public Income save(String type, double cash, int id) {

        User user = userRepository.findById(id).get();

        if (user == null)
            return null;

        Income income = new Income();
        income.cash = cash;
        income.incomeType = type;
        income.dateIncome = new Date();
        income.user = user;

        return incomeRepository.save(income);
    }
}