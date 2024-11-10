package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.UserEasyHouse;

@Controller
@RequestMapping("")
public class LoginController {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @GetMapping("")
  public String login() {
    return "/login";
  }

  @GetMapping("/login")
  public String loginPage(Model model) {
    return "/login";
  }

  @PostMapping("/login")
  public String login(String userId, String password, Model model) {
    Map<String, Object> map = jdbcTemplate
        .queryForMap("SELECT id, user_name FROM user_master_tbl WHERE user_id = '" + userId + "' and password = '"
            + password + "'");

    UserEasyHouse userEasyHouse = new UserEasyHouse();
    userEasyHouse.setId((String) map.get("id").toString());
    userEasyHouse.setName((String) map.get("user_name"));
    model.addAttribute("userEasyHouse", userEasyHouse);
    return "kakeiboNyuryoku";
  }
}
