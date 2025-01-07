package com.example.demo.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @Autowired
    MessageSource messageSource;

    @GetMapping("/")
    public String login(Model model, Locale locale) {
        model.addAttribute("title", messageSource.getMessage("app.title.login", null, locale));
        return "login";
    }
}