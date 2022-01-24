package com.example.courseProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignInController {
    @GetMapping("/signIn")
    public String getSignIn() {
        return "signIn";
    }

    @PostMapping("/register")
    public String getSignUp() {
        return "signUp";
    }
}
