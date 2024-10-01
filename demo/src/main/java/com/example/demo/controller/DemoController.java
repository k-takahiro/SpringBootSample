package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.User;

@Controller
public class DemoController {

    @GetMapping("/form")
    public String Form(Model model) {
     model.addAttribute("user", new User());
        return "form";
    }

    @PostMapping("/form")
    public String Confirm(@ModelAttribute("user") @Validated User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<String> errorList = result.getAllErrors().stream()
                .map(e -> e.getDefaultMessage())
                .collect(Collectors.toList());
            model.addAttribute("validationError", errorList);
            // エラーの再度入力画面へ
            return "form";
        }
        model.addAttribute("user", user);
        // エラーなしは確認画面へ
        return "confirm";
    }

 // 新規登録画面へ遷移
 @GetMapping("/insert")
 public String goInsert() {

     return "insert";
 }

//  // 新規登録画面へ遷移
//  @PostMapping("/insert")
//  public String insert(Model model, Account account) {

//      account = service.insertAccount(account);
//      model.addAttribute("account", account);
//      return "account/insertComplete";
//  }
}
