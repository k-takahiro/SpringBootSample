package com.example.demo.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.demo.model.User;

@Controller
// @RequestMapping("userList")
public class UserDetailsController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

   // @RequestMapping(method = RequestMethod.GET)
   @GetMapping("/user/{id}")
    public String displayView(int id, Model model) {

        Map<String, Object> map = jdbcTemplate
                .queryForMap("SELECT * FROM user_master_tbl WHERE id = " + id);
        User user = new User();
        user.setId((String) map.get("id").toString());
        user.setName((String) map.get("user_name"));
        user.setEmail((String) map.get("e_mail"));
        user.setAge(Integer.parseInt((String) map.get("age").toString()));
        user.setInputDate((String) map.get("use_start_date").toString());
        user.setUser_phone((String) map.get("user_phone").toString());
        user.setUserId((String) map.get("user_id"));
        user.setPassword((String) map.get("password"));
        user.setUser_insert_date((String) map.get("user_insert_date").toString());
        user.setUser_update_date((String) map.get("user_update_date").toString());
        model.addAttribute("userDetails", user);
        return "userDetails";
    }

}
