package com.example.demo.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @Max(value = 100, message = "正しい年齢を入力してください")
    private Integer age;
}
