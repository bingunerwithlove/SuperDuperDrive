package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping()
    public String loginView() {
        return "login";
    }

    @PostMapping()
    public String handleLogin(@RequestParam("username") String username,
                              @RequestParam("password") String password) {
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        return "home";
    }
}