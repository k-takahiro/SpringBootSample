package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.model.UserEasyHouse;

@Controller
public class KakeiboKantanConfirmController {

    // ===================================================================
    // このコントローラークラスに↓のプログラム（goInsertメソッド）があり、ログインができなかった。
    // おそらく、ログインコントローラークラスからの転記の際に、削除せずに残置してしまったメソッドである。
    // 再現で、下記メソッドのコメントアウト解除して、「localhost:8080」を実行した場合、
    // ログイン画面へ遷移不能の状態となった。
    // @GetMapping（@・・・Mapping）の設定値がどこかのクラスと重複（2つ以上）した場合に、
    // コントローラーが”どこのクラス”を評価して実行するのか、判定不能としてエラーを出力した、と予想している。
    // @GetMapping("/login")
    // public String goInsert(Model model) {
    //     return "login";
    // }
    // ===================================================================

    @PostMapping("/kakeiboNyuryoku")
    public String Confirm(@ModelAttribute @Validated UserEasyHouse userEasyHouse, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<String> errorList = result.getAllErrors().stream()
                    .map(e -> e.getDefaultMessage())
                    .collect(Collectors.toList());
            model.addAttribute("validationError", errorList);
            
            // エラーの場合は再度入力画面へ
            return "kakeiboNyuryoku";
        }
        model.addAttribute("userEasyHouse", userEasyHouse);
        // エラーなしは確認画面へ
        return "kakeiboNyuryokuConfirm";
    }
}
