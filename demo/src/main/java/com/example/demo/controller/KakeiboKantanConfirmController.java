package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.model.UserEasyHouse;

@Controller
public class KakeiboKantanConfirmController {

    @GetMapping("/login")
    public String goInsert(Model model) {
        return "login";
    }

    @PostMapping("/kakeiboNyuryokuCtrl")
    public String Confirm(@ModelAttribute @Validated UserEasyHouse userEasyHouse, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<String> errorList = result.getAllErrors().stream()
                    .map(e -> e.getDefaultMessage())
                    .collect(Collectors.toList());
            model.addAttribute("validationError", errorList);
            // エラーの場合は再度入力画面へ
            return "kakeiboNyuryoku";
        }
        model.addAttribute("userEasyHouse", userEasyHouse);
        // エラーなしは確認画面へ
        return "kakeiboNyuryoku";
    }
}
