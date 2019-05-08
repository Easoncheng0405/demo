package com.jlu.xudong.demo;

import com.jlu.xudong.demo.model.Deal;
import com.jlu.xudong.demo.model.History;
import com.jlu.xudong.demo.model.User;

import com.jlu.xudong.demo.repository.DealRepository;
import com.jlu.xudong.demo.repository.HistoryRepository;
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
    private final HistoryRepository historyRepository;

    @Autowired
    public DealController(DealRepository dealRepository, UserRepository userRepository,
            HistoryRepository historyRepository) {
        this.dealRepository = dealRepository;
        this.userRepository = userRepository;
        this.historyRepository = historyRepository;
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
        Response<History> responseHistory = new Response<>();

        User user = userRepository.findById(id).get();
        List<Deal> list = dealRepository.findByUser(user);
        for (Deal i : list) {
            responseHistory.body = new History();
            responseHistory.body.cash = i.cash;
            responseHistory.body.date = i.date;
            responseHistory.body.hKey = i.id;
            responseHistory.body.user = user;
            responseHistory.body.form = "删除";
            responseHistory.body.mark = i.mark;
            responseHistory.body.type = i.type;
            historyRepository.save(responseHistory.body);
            dealRepository.delete(i);
        }
        return response;
    }

    @GetMapping("/dealDeleteSelect")
    public Response<Void> deleteSelect(int id) {
        Response<Void> response = new Response<>();
        Response<History> responseHistory = new Response<>();
        responseHistory.body = new History();
        Deal deal = dealRepository.findById(id).get();
        responseHistory.body.cash = deal.cash;
        responseHistory.body.date = deal.date;
        responseHistory.body.hKey = deal.id;
        responseHistory.body.user = deal.user;
        responseHistory.body.form = "删除";
        responseHistory.body.mark = deal.mark;
        responseHistory.body.type = deal.type;
        historyRepository.save(responseHistory.body);
        dealRepository.delete(deal);
        return response;
    }

    @GetMapping("/dealUpdata")
    public Response<Deal> updata(int id, String type, double cash) {
        Response<Deal> response = new Response<>();
        Response<History> responseHistory = new Response<>();
        // response.body = new Deal();
        response.body = dealRepository.findById(id).get();
        responseHistory.body = new History();
        responseHistory.body.cash = response.body.cash;
        responseHistory.body.date = response.body.date;
        responseHistory.body.user = response.body.user;
        responseHistory.body.type = response.body.type;
        responseHistory.body.hKey = response.body.id;
        responseHistory.body.mark = response.body.mark;
        responseHistory.body.form = "修改";
        historyRepository.save(responseHistory.body);

        response.body.cash = cash;
        response.body.type = type;
        dealRepository.save(response.body);
        return response;
    }

    @GetMapping("/historyrestore")
    public Response<Deal> historyUpdata(int id, String type, double cash) {

        Response<Deal> responseDeal = new Response<>();

        responseDeal.body = dealRepository.findById(id).get();

        responseDeal.body.cash = cash;
        responseDeal.body.type = type;
        dealRepository.save(responseDeal.body);

        return responseDeal;
    }
}