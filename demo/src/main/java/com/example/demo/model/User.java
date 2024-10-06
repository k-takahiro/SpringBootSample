package com.example.demo.model;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class User {
    @NotBlank(message = "名前を入力してください")
    @Size(max = 10, message = "名前は10桁以内で入力してください")
    private String name;

    @NotBlank(message = "メールアドレスを入力してください")
    @Email
    private String email;

    @NotNull(message = "年齢を入力してください")
    @Min(value = 18, message = "年齢は18才以上で入力してください")
    @Max(value = 100, message = "年齢は100才以下で入力してください")
    private Integer age;

    @NotNull(message = "日付を入力してください")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String inputDate;
    // private Data inputDate; // 型がDataはエラーになるためコメントアウトした。
}
