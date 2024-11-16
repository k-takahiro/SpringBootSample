package com.example.demo.model;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UIItem {

    // 都道府県コード
    private String prefcode;

    // 都道府県名
    private String prefname;

}
