package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("userKakeibo")
public class UserKakeiboController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(method = RequestMethod.GET)
    public String getAllUser(Model model) {
        //List<User> userList = selectMany();

        // 画面表示
       // model.addAttribute("userList", userList);
        return "userList";
    }

}
