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
public class User {

    // 利用者ID
    private String id;

    // 利用者氏名
    @NotBlank(message = "名前を入力してください")
    @Size(max = 10, message = "名前は10桁以内で入力してください")
    private String name;

    // 利用者メールアドレス
    @NotBlank(message = "メールアドレスを入力してください")
    @Email
    private String email;

    // 利用者年齢
    @NotNull(message = "年齢を入力してください")
    @Min(value = 18, message = "年齢は18才以上で入力してください")
    @Max(value = 100, message = "年齢は100才以下で入力してください")
    private Integer age;

    // 利用者利用開始日付
    @NotNull(message = "日付を入力してください")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String inputDate;
    // private Data inputDate; // 型がDataはエラーになるためコメントアウトした。

    // 利用者電話番号 
    // TODO: javascriptにてバリデーションを実装したい。
    private String user_phone;

    // 利用者ユーザID
    @NotBlank(message = "ユーザIDを入力してください")
    private String userId;

    // 利用者パスワード
    @NotBlank(message = "パスワードを入力してください")
    private String password;

    // 利用者登録日時
    private String user_insert_date;

    // 利用者更新日時
    private String user_update_date;
}
