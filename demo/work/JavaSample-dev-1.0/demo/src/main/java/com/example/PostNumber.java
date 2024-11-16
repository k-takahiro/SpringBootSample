package com.example;

import org.apache.commons.lang3.StringUtils;

public class PostNumber {
    private String value;
    private String formatedValue;

    public String getValue() {
        return value;
    }
    public String getFormatedValue() {
        return formatedValue;
    }
    public PostNumber(String formatedValue) {
        if (StringUtils.isBlank(formatedValue)) {
            throw new IllegalArgumentException();
        }

        this.formatedValue = formatedValue;
        // ハイフンを除去
        this.value = formatedValue.replaceAll("-", "");
    }
    public static PostNumber valueOf(String value) {
        return new PostNumber(value);
    }
}
