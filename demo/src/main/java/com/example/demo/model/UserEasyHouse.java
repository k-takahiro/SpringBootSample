package com.example.demo.model;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserEasyHouse {

    // 利用者ID
    private String id;

    // 氏名
    private String name;

    // 対象日
    @NotNull(message = "対象日を入力してください")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String target_date;

    // 収入
    @NotNull(message = "収入を入力してください")
    private Integer income;

    // 支出
    @NotNull(message = "支出を入力してください")
    private Integer expenses;

    // 収支
    private Integer income_and_expenditure;
    
    // コメント
    @NotNull(message = "コメントを入力してください")
    private String comments;

    // ステータス
    @NotNull(message = "ステータスを入力してください")
    private String status;

    // 利用者登録日時
    private String user_insert_date;

    // 利用者最終更新日時
    private String user_update_date;
}
