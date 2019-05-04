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

    // @GetMapping("/register")
    // public String register(String name, String password) {
    // User user = new User();
    // user.name = name;
    // user.password = password;
    // if (userRepository.save(user) != null) {
    // return "success";
    // }
    // return "error";
    // }
    @GetMapping("/register")
    public Response<User> register(String name, String password) {
        Response<User> response = new Response<>();
        response.body = new User();
        response.body.name = name;
        response.body.password = password;
        if (userRepository.save(response.body) == null) {
            response.status = 400;
            response.msg = "create fail";
        }
        return response;

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

    @GetMapping("/findIDByName")
    public Response<User> findIdByName(String name) {
        Response<User> response = new Response<>();
        response.body = new User();
        response.body = userRepository.findByName(name);
        return response;
    }

    @GetMapping("/deleteuser")
    public Response<Void> delete(int id) {
        Response<Void> response = new Response<>();
        userRepository.deleteById(id);

        return response;
    }

    @GetMapping("/updatapassword")
    public Response<User> updataPassword(int id, String password) {
        Response<User> response = new Response<>();
        response.body = userRepository.findById(id).get();
        response.body.password = password;
        if (userRepository.save(response.body) != null) {
            response.status = 200;
            response.msg = "success";
        }
        return response;
    }
}