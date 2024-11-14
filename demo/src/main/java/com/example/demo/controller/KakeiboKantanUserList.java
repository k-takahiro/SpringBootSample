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
import com.example.demo.model.UserEasyHouse;

@Controller
@RequestMapping("kakeiboKantanUserList")
public class KakeiboKantanUserList {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(method = RequestMethod.GET)
    public String getAllUser(Model model) {

        // Userテーブルの全データを取得.
        List<Map<String, Object>> getList = jdbcTemplate
                .queryForList("SELECT * FROM public.easy_plan_household_account");

        // 結果返却用の変数：userList
        // 取得したデータを結果返却用のListに格納していく
        // 結果返却用のListに追加
        List<UserEasyHouse> userList = new ArrayList<>();
        for (Map<String, Object> map : getList) {
            UserEasyHouse user = new UserEasyHouse();
            user.setId((String) map.get("id").toString());
            // user.setName((String) map.get("user_name"));
            user.setTarget_date((String) map.get("target_date").toString());
            user.setIncome((Integer) map.get("income"));
            user.setExpenses((Integer) map.get("expenses"));
            user.setComments((String) map.get("comments"));
            user.setStatus((String) map.get("status"));
            user.setUser_insert_date((String) map.get("insert_date").toString());
            user.setUser_update_date((String) map.get("update_date").toString());

            userList.add(user);

            // 画面表示
            model.addAttribute("kakeiboKantan", userList);
            
        }
        return "kakeiboKantanUserList";
    }
}
