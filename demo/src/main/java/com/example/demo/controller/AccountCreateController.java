package com.example.demo.controller;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.User;

@Controller
public class AccountCreateController {

    @Autowired
    MessageSource messageSource;

    // メッセージの外部化(Controllerでメッセージを取得する場合)
    @GetMapping("/accountCreateFormView")
    public String getAccountCreateFormView(@Validated User user, BindingResult result, Model model, Locale locale) {
        model.addAttribute("title", messageSource.getMessage("app.title.account.create", null, locale));
        return "accountCreateForm";
    }

    @PostMapping("/accountCreateForm")
    public String Confirm(@ModelAttribute @Validated User user, BindingResult result, Model model, Locale locale) {
        if (result.hasErrors()) {
            List<String> errorList = result.getAllErrors().stream()
                    .map(e -> e.getDefaultMessage())
                    .collect(Collectors.toList());
            model.addAttribute("validationError", errorList);
            model.addAttribute("title", messageSource.getMessage("app.title.account.create", null, locale));
            // エラーの場合は再度入力画面へ
            return "accountCreateForm";
        }

        // プロパティファイル名に「_en」が含まれると、タイトルメッセージが取得できない。(messages_en.properties)
        model.addAttribute("title", messageSource.getMessage("app.title.en", null, locale));
        model.addAttribute("user", user);
        // エラーなしは確認画面へ
        return "accountCreateFormConfirm";
    }

}
