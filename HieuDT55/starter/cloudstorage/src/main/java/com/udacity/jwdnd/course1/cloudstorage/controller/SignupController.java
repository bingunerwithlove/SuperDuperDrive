package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import com.udacity.jwdnd.course1.cloudstorage.model.SuperUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SignupController {

    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/signup")
    public String signupView() {
        return "signup";
    }

    @PostMapping("/signup")
    public String register(@ModelAttribute("SpringWeb") SuperUser superUser, Model model) {
        if (superUser == null) {
            model.addAttribute("signupError", "Invalid input.");
            return "signup";
        }

        try {
            userService.register(superUser);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("signupError", "Username already exists. Please choose a different username.");
            return "signup";
        }

        model.addAttribute("signupSuccess", "You have successfully signed up! Please login.");
        return "login";
    }
}
