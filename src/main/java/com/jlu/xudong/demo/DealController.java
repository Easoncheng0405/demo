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

    @GetMapping("/dealShowAll")
    public Response<List<Deal>> showAll(int id) { // 用户id
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

    @GetMapping("/dealshowSelect")
    public Response<Deal> showSelect(int id) { // 收入记录id
        Response<Deal> response = new Response<>();
        response.body = dealRepository.findById(id).get();
        return response;
    }

    @GetMapping("/dealSave")
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
        response.body.mark = mark;

        response.body = dealRepository.save(response.body);
        return response;
    }

    @GetMapping("/dealDeleteAll")
    public Response<Void> deleteAll(int id) {
        Response<Void> response = new Response<>();
        User user = userRepository.findById(id).get();
        List<Deal> list = dealRepository.findByUser(user);
        for (Deal i : list) {
            dealRepository.delete(i);
        }
        return response;
    }

    @GetMapping("/dealDeleteSelect")
    public Response<Void> deleteSelect(int id) {
        Response<Void> response = new Response<>();
        dealRepository.deleteById(id);
        return response;
    }

    @GetMapping("/dealUpdata")
    public Response<Deal> updata(int id, String type, double cash) {
        Response<Deal> response = new Response<>();
        response.body = dealRepository.findById(id).get();
        response.body.cash = cash;
        response.body.type = type;
        dealRepository.save(response.body);
        return response;
    }
}