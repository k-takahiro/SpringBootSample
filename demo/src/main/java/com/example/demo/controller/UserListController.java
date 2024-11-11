package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.model.User;

@Controller
@RequestMapping("userList")
public class UserListController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(method = RequestMethod.GET)
    public String getAllUser(Model model) {
        List<User> userList = selectMany();

        // 画面表示
        model.addAttribute("userList", userList);
        return "userList";
    }

    public List<User> selectMany() throws DataAccessException {
        // Userテーブルの全データを取得.
        List<Map<String, Object>> getList = jdbcTemplate.queryForList("SELECT id, user_name FROM user_master_tbl");

        // 結果返却用の変数：userList
        // 取得したデータを結果返却用のListに格納していく
        // 結果返却用のListに追加
        List<User> userList = new ArrayList<>();
        for (Map<String, Object> map : getList) {
            User user = new User();
            user.setId((String) map.get("id").toString());
            user.setName((String) map.get("user_name"));
            userList.add(user);
        }
        return userList;
    }
}
