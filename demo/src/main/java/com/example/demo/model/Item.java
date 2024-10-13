package com.example.demo.model;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class Item {
    @NotNull(message = "日付を入力してください")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String itemInsertDate;
    
    @NotNull(message = "種別を選択してください")
    private String itemKind;
    
    @NotNull(message = "品名を入力してください")
    private String itemName;

    @NotNull(message = "金額を入力してください")
    private String itemPrice;
}
