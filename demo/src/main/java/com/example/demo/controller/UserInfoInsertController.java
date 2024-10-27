package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.demo.model.User;

@Controller
public class UserInfoInsertController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/userInfoInsert")
    public String userInsert(Model model, User user) throws ParseException {

        String sqlText = """
                INSERT
                INTO public.user_master_tbl(
                	id
                    ,user_name
                	, e_mail
                	, age
                	, use_start_date
                	, user_phone
                	, user_id
                	, password
                )
                VALUES (
                	?
                    ,?
                	, ?
                	, ?
                	, ?
                	, ?
                	, ?
                	, ?
                )
                                """;

        String str = "SELECT setval(pg_get_serial_sequence('user_master_tbl', 'id'), (SELECT MAX(id) FROM user_master_tbl));";
        var num = jdbcTemplate.queryForObject(str, Integer.class);

        var useStartDay = user.getInputDate().replace("-", "/");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = dateFormat.parse(useStartDay);

        jdbcTemplate.update(sqlText, (num + 1), user.getName(), user.getEmail(), user.getAge(), date, user.getUser_phone(),
                user.getUserId(), user.getPassword());

        return "login";
    }
}
