package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.demo.model.UserEasyHouse;

@Controller
public class UserEasyHouseInsertController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/userCtrlKakeibo")
    public String userInsert(Model model, UserEasyHouse userEasyHouse) throws ParseException {

        String sqlText = """
                                INSERT
                INTO public.easy_plan_household_account(
                	id
                	, target_date
                	, income
                	, expenses
                	, comments
                	, status
                	, insert_date
                	, update_date
                )
                VALUES (
                	?
                	, ?
                	, ?
                	, ?
                	, ?
                	, ?
                	, ?
                	, ?
                )
                                                """;

        var useStartDay = userEasyHouse.getTarget_date().replace("-", "/");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = dateFormat.parse(useStartDay);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        jdbcTemplate.update(sqlText, userEasyHouse.getId(), date, userEasyHouse.getIncome(),
                userEasyHouse.getExpenses(), userEasyHouse.getComments(), userEasyHouse.getStatus(),
                timestamp, timestamp);

        return "login";
    }
}
