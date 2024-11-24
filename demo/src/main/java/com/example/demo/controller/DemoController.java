package com.example.demo.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
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

import com.example.demo.model.User;

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

        // 生年月日のプルダウンリスト項目を作成する。
        // Configurator.getInstance().getValueByKey("key1"); TODO:設定値は一旦直書き(マジックナンバー)する。
        LocalDate startDay = LocalDate.of(2000, 1, 1);
        LocalDate endDay = LocalDate.of(2010, 1, 11);
        long diffDay = ChronoUnit.DAYS.between(startDay, endDay);
        var dateOfBirthList = new LinkedList<LocalDate>();
        for (int i = 0; i < Math.toIntExact(diffDay); i++) {
            dateOfBirthList.add(startDay.plusDays(i));
        }

        var yyyy = "";
        var yyyyList = new ArrayList<String>();
        var mmddList = new ArrayList<String>();
        for (var day : dateOfBirthList){
            if (!yyyy.equals(String.valueOf( day.getYear()))){
                yyyyList.add(String.valueOf( day.getYear()));
                yyyy = String.valueOf( day.getYear());
            }
            mmddList.add (String.valueOf(day.getMonthValue()) + "-" + String.valueOf( day.getDayOfMonth()));
        }

        model.addAttribute("birthYearList", yyyyList);
        model.addAttribute("mmddList", mmddList);

		// LocalDateの月末日を取得
		LocalDate targetDate = LocalDate.of(2020, 2, 1);
		LocalDate result = targetDate.with(TemporalAdjusters.lastDayOfMonth());
		System.out.println("LocalDate.with(TemporalAdjusters.lastDayOfMonth()) = " + result);

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
