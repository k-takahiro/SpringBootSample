package com.example.demo.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class User {

    @NotBlank(message = "{NotBlank.User.name}")
    @Size(max = 10, message = "{Max.User.name}")
    private String name;

    @NotBlank(message = "{NotBlank.User.email}")
    @Email(message = "{Email.User.email}")
    private String email;

    @NotNull(message = "{NotNull.User.age}")
    @Max(value = 100, message = "{Max.User.age}")
    private Integer age;

}
