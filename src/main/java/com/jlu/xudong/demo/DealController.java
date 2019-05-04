package com.jlu.xudong.demo;

import com.jlu.xudong.demo.model.Deal;
import com.jlu.xudong.demo.model.User;

import com.jlu.xudong.demo.repository.DealRepository;
import com.jlu.xudong.demo.repository.UserRepository;
import com.jlu.xudong.demo.net.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import java.util.ArrayList;
import java.util.Date;

@RestController

public class DealController {

    private final DealRepository dealRepository;
    private final UserRepository userRepository;

    @Autowired
    public DealController(DealRepository dealRepository, UserRepository userRepository) {
        this.dealRepository = dealRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/dealIncome")
    public Response<List<Deal>> income(int id) { // 用户id
        Response<List<Deal>> response = new Response<>();
        User user = new User();
        user = userRepository.findById(id).get();

        if (user == null) {
            response.body = new ArrayList<>(0);
            return response;
        }
        response.body = dealRepository.findByUser(user);
        return response;
    }

    @GetMapping("/dealIncomeSelect")
    public Response<Deal> incomeSelect(int id) { // 收入记录id
        Response<Deal> response = new Response<>();
        response.body = dealRepository.findById(id).get();
        return response;
    }

    @GetMapping("/dealSaveIncome")
    public Response<Deal> save(String type, double cash, int id, boolean mark) {
        Response<Deal> response = new Response<>();
        User user = new User();
        user = userRepository.findById(id).get();

        if (user == null) {
            response.status = 404;
            return response;
        }

        response.body = new Deal();
        response.body.cash = cash;
        response.body.type = type;
        response.body.date = new Date();
        response.body.user = user;
        response.body.mark = true;

        response.body = dealRepository.save(response.body);
        return response;
    }

    @GetMapping("/dealDeleteAllIncome")
    public Response<Void> deleteAllIncome(int id) {
        Response<Void> response = new Response<>();
        User user = userRepository.findById(id).get();
        List<Deal> list = dealRepository.findByUser(user);
        for (Deal i : list) {
            dealRepository.delete(i);
        }
        return response;
    }

    @GetMapping("/dealDeleteIncome")
    public Response<Void> deleteIncome(int id) {
        Response<Void> response = new Response<>();
        dealRepository.deleteById(id);
        return response;
    }

    @GetMapping("/dealUpdataIncome")
    public Response<Deal> updataIncome(int id, String type, double cash) {
        Response<Deal> response = new Response<>();
        response.body = dealRepository.findById(id).get();
        response.body.cash = cash;
        response.body.type = type;
        dealRepository.save(response.body);
        return response;
    }

    @GetMapping("/dealOutcome")
    public Response<List<Deal>> outcome(int id) {
        Response<List<Deal>> response = new Response<>();
        User user = userRepository.findById(id).get();

        if (user == null) {
            response.body = new ArrayList<>(0);
            return response;
        }
        response.body = dealRepository.findByUser(user);
        return response;
    }

    @GetMapping("/dealOutcomeSelect")
    public Response<Deal> outcomeSelect(int id) {
        Response<Deal> response = new Response<>();
        response.body = dealRepository.findById(id).get();
        return response;
    }

    @GetMapping("/dealSaveOutCome")
    public Response<Deal> save(int id, String type, double cash, boolean mark) {
        Response<Deal> response = new Response<>();
        User user = userRepository.findById(id).get();

        if (user == null)
            return null;

        response.body = new Deal();
        response.body.cash = cash;
        response.body.type = type;
        response.body.date = new Date();
        response.body.user = user;
        response.body.mark = false;
        response.body = dealRepository.save(response.body);

        return response;
    }

    @GetMapping("/dealSelectAllOutcome")
    public Response<Void> delectAllOutcome(int id) {
        Response<Void> response = new Response<>();
        User user = userRepository.findById(id).get();
        List<Deal> list = dealRepository.findByUser(user);
        for (Deal i : list) {
            dealRepository.delete(i);
        }
        return response;
    }

    @GetMapping("/dealSeleteOutcome")
    public Response<Void> deleteOutcome(int id) {
        Response<Void> response = new Response<>();
        dealRepository.deleteById(id);
        return response;
    }

    @GetMapping("/dealUpdataOutcomeType")
    public Response<Deal> updataOutcometype(int id, String type) {
        Response<Deal> response = new Response<>();
        response.body = dealRepository.findById(id).get();
        response.body.type = type;
        if (dealRepository.save(response.body) != null) {
            response.status = 200;
            response.msg = "success";
        }
        return response;
    }

    @GetMapping("/dealUpdataOutcomeCash")
    public Response<Deal> updataOutcomecash(int id, double cash) {
        Response<Deal> response = new Response<>();
        response.body = dealRepository.findById(id).get();
        response.body.cash = cash;
        if (dealRepository.save(response.body) != null) {
            response.status = 200;
            response.msg = "success";
        }
        return response;
    }

    @GetMapping("/dealUpdataOutcome")
    public Response<Deal> updataOutcome(int id, String type, double cash) {
        Response<Deal> response = new Response<>();
        response.body = dealRepository.findById(id).get();
        response.body.cash = cash;
        response.body.type = type;
        if (dealRepository.save(response.body) != null) {
            response.status = 200;
            response.msg = "success";
        }
        return response;
    }
}