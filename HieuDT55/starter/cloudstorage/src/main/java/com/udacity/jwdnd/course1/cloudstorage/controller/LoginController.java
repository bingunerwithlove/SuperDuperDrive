package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.SuperUser;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginView(Authentication authentication) {
//        System.out.println(authentication);
//        System.out.println(authentication.isAuthenticated());
        if (authentication != null) {
            if (authentication.isAuthenticated()) {
                return "home";
            }
        }
        return "login";
    }

//    @PostMapping("/login")
//    public String handleLogin(
//            @RequestParam("username") String username,
//            @RequestParam("password") String password,
//            Authentication authentication,
//            Model model
//    )
//    {
//        SuperUser user = userService.findByUsername(username);
//        System.out.println(user);
//        if (user != null && userService.checkPassword(user, password)) {
//            System.out.println("login success");
//            model.addAttribute("username", user.getUsername());
//            System.out.println(authentication);
//            return "home";
//        } else {
//            System.out.println("login error");
//            model.addAttribute("loginError", true);
//            return "login";
//        }
//    }

    @PostMapping("/logout")
    public String handleLogout() {
        return "login";
    }
}