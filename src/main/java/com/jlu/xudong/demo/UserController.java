package com.jlu.xudong.demo;

import com.jlu.xudong.demo.model.User;
import com.jlu.xudong.demo.net.Response;
import com.jlu.xudong.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public String register(String name, String password) {
        User user = new User();
        user.name = name;
        user.password = password;
        if (userRepository.save(user) != null) {
            return "success";
        }
        return "error";
    }

    @GetMapping("/login")
    public Response<User> login(String name, String password) {
        Response<User> response = new Response<>();
        response.body = userRepository.findByNameAndPassword(name, password);
        if (response.body == null) {
            response.status = 404;
            response.msg = "user not found";
        }
        return response;
    }
}