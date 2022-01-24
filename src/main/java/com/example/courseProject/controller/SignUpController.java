package com.example.courseProject.controller;

import com.example.courseProject.forms.SignUpForm;
import com.example.courseProject.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;
import java.text.ParseException;

@RequiredArgsConstructor
@Controller
public class SignUpController {

    private final SignUpService signUpService;

    @GetMapping("/signUp")
    public String getSignUp() {
        return "signUp";
    }

    @PostMapping("/signUp")
    public String signUpUser(@Valid SignUpForm form, BindingResult result) throws ParseException {
        signUpService.signUpUser(form);
        return "redirect:/signIn";
    }

    @PostMapping("/registered")
    public String signUpUserPreviouslyRegistered() {
        return "redirect:/signIn";
    }
}
