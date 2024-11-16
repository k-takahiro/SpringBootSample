package com.example.demo.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.UIItem;
import com.example.demo.model.User;
import com.example.demo.model.UserEasyHouse;

@Controller
public class DemoController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/form")
    public String Form(Model model) {

        List<Map<String, Object>> prefList = jdbcTemplate
                .queryForList("SELECT prefcode , prefname FROM public.prefmastertbl");

        // プルダウンリストを作成
        Map<String, String> itemKindMap = new LinkedHashMap<String, String>();
        for (var prefPair : prefList) {
            itemKindMap.put ((String) prefPair.get("prefcode"), (String) prefPair.get("prefname"));
        }

        model.addAttribute("user", new User());
        model.addAttribute("itemKindMap", itemKindMap);

        return "form";
    }

    @PostMapping("/form")
    public String Confirm(@ModelAttribute @Validated User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<String> errorList = result.getAllErrors().stream()
                    .map(e -> e.getDefaultMessage())
                    .collect(Collectors.toList());
            model.addAttribute("validationError", errorList);
            // エラーの場合は再度入力画面へ
            return "form";
        }
        model.addAttribute("user", user);
        // エラーなしは確認画面へ
        return "confirm";
    }
}
