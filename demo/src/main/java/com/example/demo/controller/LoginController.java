package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class LoginController {

  @Autowired
  private UserService userService;

  @Autowired
  private HttpSession session;

  @GetMapping("")
  public String login() {
    return "/login";
  }

  @GetMapping("/login")
  public String loginPage(Model model) {
    return "/login";
  }

  /**
   * ログイン処理を行う
   * 
   * @param name     ユーザー名
   * @param password パスワード
   * @param model    モデル
   * @return ログイン成功時は/home、失敗時は/loginに遷移
   */
  @PostMapping("/login")
  public String login(String name, String password, Model model) {
    return userService.findByUser(name, password)
        .map(user -> {
          session.setAttribute("user", user);
          return "/home";
        })
        .orElseGet(() -> {
          model.addAttribute("name", name);
          model.addAttribute("message", "ユーザー名またはパスワードが違います");
          return "/login";
        });

  }

  @GetMapping("/home")
  public String home() {
    if (session.getAttribute("user") == null) {
      return "redirect:/login";
    }
    return "/home";
  }
}
