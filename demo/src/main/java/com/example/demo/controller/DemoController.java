package com.example.demo.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.UIItem;
import com.example.demo.model.User;
import com.example.demo.model.UserEasyHouse;

@Controller
public class DemoController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/form")
    public String Form(Model model) {

        // 都道府県テーブルから都道府県コード、都道府県名を取得して都道府県リストへ代入する。
        // 都道府県を設定するプルダウンリストを作成する。
        List<Map<String, Object>> prefList = jdbcTemplate
                .queryForList("SELECT prefcode , prefname FROM public.prefmastertbl");
        Map<String, String> itemKindMap = new LinkedHashMap<String, String>();
        for (var prefPair : prefList) {
            itemKindMap.put((String) prefPair.get("prefcode"), (String) prefPair.get("prefname"));
        }
        model.addAttribute("itemKindMap", itemKindMap);
        model.addAttribute("user", new User());

        List<String> dateList = new LinkedList<>(); 
        LocalDate strDay = LocalDate.of(1900, 1, 1);
        LocalDate endDay = LocalDate.of(2020,12,31);
        long localDiffDays1 = ChronoUnit.DAYS.between(strDay, endDay);

        for (int i = 0; i < Math.toIntExact(localDiffDays1); i++){
            dateList.add(strDay.plusDays(i).toString());
        }

        // 文字列の分割
        

        // 生年月日を設定するプルダウンリストを作成する。
        int currentYear = YearMonth.now().getYear();
        // 年プルダウン
        List<String> birthYearList = new ArrayList<>();
        for (int i = currentYear-70; i <= currentYear-20; i++) {
            birthYearList.add(String.valueOf(i));
        }
        // 月プルダウン
        List<String> monthList = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            monthList.add(String.valueOf(i));
        }
        // 初期値
        String birthYearDefault = String.valueOf(currentYear-30);

        model.addAttribute("birthYearList", birthYearList);
        model.addAttribute("birthYearDefault", birthYearDefault);
        model.addAttribute("monthList", monthList);
        return "form";
    }

    @PostMapping("/form")
    public String Confirm(@ModelAttribute @Validated User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<String> errorList = result.getAllErrors().stream()
                    .map(e -> e.getDefaultMessage())
                    .collect(Collectors.toList());
            model.addAttribute("validationError", errorList);
            // エラーの場合は再度入力画面へ
            return "form";
        }
        model.addAttribute("user", user);
        // エラーなしは確認画面へ
        return "confirm";
    }
}
