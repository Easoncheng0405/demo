package com.jlu.xudong.demo;

import com.jlu.xudong.demo.model.History;
import com.jlu.xudong.demo.model.User;

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

public class HistoryController {

    private final HistoryRepository historyRepository;
    private final UserRepository userRepository;

    @Autowired
    public HistoryController(HistoryRepository historyRepository, UserRepository userRepository) {
        this.historyRepository = historyRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/historySave")

    public Response<History> save(String type, double cash, int id, boolean mark, int aid, String form) {
        Response<History> response = new Response<>();
        User user = new User();
        user = userRepository.findById(id).get();

        if (user == null) {
            response.status = 404;
            return response;
        }

        response.body = new History();
        response.body.cash = cash;
        response.body.type = type;
        response.body.date = new Date();
        response.body.user = user;
        response.body.mark = mark;
        response.body.id = aid; // 进行操作的记录的id
        response.body.form = form; // 删除，修改两种字段

        response.body = historyRepository.save(response.body);
        return response;
    }

    @GetMapping("/historyShowAll")
    public Response<List<History>> showAll(int id) { // 用户id
        Response<List<History>> response = new Response<>();
        User user = new User();
        user = userRepository.findById(id).get();

        if (user == null) {
            response.body = new ArrayList<>(0);
            return response;
        }
        response.body = historyRepository.findByUser(user);
        return response;
    }

    @GetMapping("/historyDeleteSelect")
    public Response<Void> deleteSelect(int id) {
        Response<Void> response = new Response<>();
        historyRepository.deleteById(id);
        return response;
    }

}