package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class LoginController {

  @Autowired
  private UserService userService;

  @Autowired
  private HttpSession session;

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

  /**
   * ログイン処理を行う
   * 
   * @param name     ユーザー名
   * @param password パスワード
   * @param model    モデル
   * @return ログイン成功時は/home、失敗時は/loginに遷移
   */
  // @PostMapping("/login")
  // public String login(String userId, String password, Model model) {
  //   return userService.findByUser(userId, password)
  //       .map(user -> {
  //         session.setAttribute("user", user);
  //         return "/home";
  //       })
  //       .orElseGet(() -> {
  //         model.addAttribute("userId", userId);
  //         model.addAttribute("message", "ユーザー名またはパスワードが違います");
  //         return "/login";
  //       });

  // }

  @GetMapping("/home")
  public String home() {
    // // sessionはもう少し進められてから書きたいのと混乱防止のためコメントアウト。
    // if (session.getAttribute("user") == null) {
    //   return "redirect:/login";
    // }
    return "/home";
  }

  
  @PostMapping("/login")
  public String login(String userId, String password, Model model) {
        Map<String, Object> map = jdbcTemplate
                .queryForMap("SELECT id, user_name FROM user_master_tbl WHERE user_id = '" + userId +  "' and password = '" +  password +"'");
        User user = new User();
        user.setId((String) map.get("id").toString());
        user.setName((String) map.get("user_name"));
        model.addAttribute("user", user);
        return "/home";
    }

}
