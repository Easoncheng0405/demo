package com.jlu.xudong.demo;

import com.jlu.xudong.demo.model.Income;
import com.jlu.xudong.demo.model.User;
import com.jlu.xudong.demo.net.Response;
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

    // @GetMapping("/income")
    // public List<Income> income(int id) {
    // User user = userRepository.findById(id).get();
    // if (user == null)
    // return new ArrayList<>(0);
    // return incomeRepository.findByUser(user);
    // }

    @GetMapping("/income")
    public Response<List<Income>> income(int id) { // 用户id
        Response<List<Income>> response = new Response<>();
        User user = new User();
        user = userRepository.findById(id).get();

        if (user == null) {
            response.body = new ArrayList<>(0);
            return response;
        }
        response.body = incomeRepository.findByUser(user);
        return response;
    }

    @GetMapping("/incomeselect")
    public Response<Income> incomeSelect(int id) { // 收入记录id
        Response<Income> response = new Response<>();
        response.body = incomeRepository.findById(id).get();
        return response;
    }

    @GetMapping("/saveIncome")
    public Response<Income> save(String type, double cash, int id) {
        Response<Income> response = new Response<>();
        User user = new User();
        user = userRepository.findById(id).get();

        if (user == null)
            return null;

        response.body = new Income();
        response.body.cash = cash;
        response.body.incomeType = type;
        response.body.dateIncome = new Date();
        response.body.user = user;

        response.body = incomeRepository.save(response.body);
        return response;
    }

    @GetMapping("/deleteallIncome")
    public Response<Void> deleteAllIncome(int id) {
        Response<Void> response = new Response<>();
        User user = userRepository.findById(id).get();
        List<Income> list = incomeRepository.findByUser(user);
        for (Income i : list) {
            incomeRepository.delete(i);
        }
        return response;
    }

    @GetMapping("/DeleteIncome")
    public Response<Void> deleteIncome(int id) {
        Response<Void> response = new Response<>();
        incomeRepository.deleteById(id);
        return response;
    }

    @GetMapping("/updataIncometype")
    public Response<Income> updataIncometype(int id, String type) {
        Response<Income> response = new Response<>();
        response.body = incomeRepository.findById(id).get();
        response.body.incomeType = type;
        if (incomeRepository.save(response.body) != null) {
            response.status = 200;
            response.msg = "success";
        }
        return response;
    }

    @GetMapping("/updataIncomecash")
    public Response<Income> updataIncomecash(int id, double cash) {
        Response<Income> response = new Response<>();
        response.body = incomeRepository.findById(id).get();
        response.body.cash = cash;
        if (incomeRepository.save(response.body) != null) {
            response.status = 200;
            response.msg = "success";
        }
        return response;
    }

    @GetMapping("/updataIncome")
    public Response<Income> updataIncome(int id, String type, double cash) {
        Response<Income> response = new Response<>();
        response.body = incomeRepository.findById(id).get();
        response.body.cash = cash;
        response.body.incomeType = type;
        if (incomeRepository.save(response.body) != null) {
            response.status = 200;
            response.msg = "success";
        }
        return response;
    }

}