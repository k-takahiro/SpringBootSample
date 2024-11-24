package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("userKakeibo")
public class UserKakeiboController {

    @RequestMapping(method = RequestMethod.GET)
    public String getAllUser(Model model) {
        //List<User> userList = selectMany();

        // 画面表示
       // model.addAttribute("userList", userList);
        return "userList";
    }

}
