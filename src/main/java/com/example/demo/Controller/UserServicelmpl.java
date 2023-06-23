package com.example.demo.Controller;

import com.example.demo.Repository.UserService;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserServicelmpl {
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public User AddUser (@RequestBody User user){
        return userService.save(user);
    }
}
