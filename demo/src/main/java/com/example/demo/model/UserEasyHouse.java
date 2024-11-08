package com.example.demo.model;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserEasyHouse {

    // 利用者ID
    private String id;

    // 氏名
    private String name;

    // 対象日
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String target_date;

    // 収入
    private Integer income;

    // 支出
    private Integer expenses;

    // 収支
    private Integer income_and_expenditure;
    
    // コメント
    private String comments;

    // ステータス
    private String status;

    // 利用者登録日時
    private String user_insert_date;

    // 利用者更新日時
    private String user_update_date;
}
