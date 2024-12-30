package com.example.mvc.model;

import lombok.Data;

// 学習帳_テーブル
@Data
public class BookmarkDto {

    // タイトル
    private String title;

    // リンク
    private String link;

    // 分類
    private String category;

    // 登録日時
    private String insert_date;

    // 最終更新日時
    private String update_date;

}
