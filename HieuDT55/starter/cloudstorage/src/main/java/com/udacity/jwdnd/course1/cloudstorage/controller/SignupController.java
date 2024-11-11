package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import com.udacity.jwdnd.course1.cloudstorage.model.SuperUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/signup")
public class SignupController {

    private final UserService userService;

    public SignupController(UserService userService) {
        System.out.println(userService);
        this.userService = userService;
    }

    @GetMapping()
    public String signupView() {
        System.out.println(5);
        return "signup";
    }

    @PostMapping("/signup")
    public String register(@ModelAttribute("SpringWeb") SuperUser superUser) {
        System.out.println(superUser);
        if (superUser == null) {
            return "redirect:signup";
        }

        try {
            userService.register(superUser);
        } catch (Exception e) {
            return "redirect:signup?error";
        }

        return "redirect:signup?success";
    }
}