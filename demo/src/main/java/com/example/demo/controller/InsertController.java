package com.example.demo.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Item;

@Controller
public class InsertController {

    // 新規登録画面へ遷移
    @GetMapping("/insert")
    public String goInsert(Model model) {
        model.addAttribute("item", new Item());

		// プルダウンリストを作成
		Map<Integer, String> itemKindMap = new LinkedHashMap<Integer, String>();
        itemKindMap.put(1, "食費");
        itemKindMap.put(2, "趣味");
        itemKindMap.put(3, "教養");
        itemKindMap.put(4, "その他");
		model.addAttribute("itemKindMap", itemKindMap);
        return "insert";
    }

    // 新規登録画面へ遷移
    @PostMapping("/insert")
    public String itemConfirm(@ModelAttribute @Validated Item item, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<String> errorList = result.getAllErrors().stream()
                    .map(e -> e.getDefaultMessage())
                    .collect(Collectors.toList());
            model.addAttribute("validationError", errorList);
            // エラーの場合は再度入力画面へ
            return "insert";
        }
        model.addAttribute("item", item);
        // エラーなしは確認画面へ
        return "ItemConfirm";
    }
}
