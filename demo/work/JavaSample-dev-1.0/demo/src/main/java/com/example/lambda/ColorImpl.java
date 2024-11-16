package com.example.lambda;

public class ColorImpl implements Color {

    @Override
    public void print(String str) {
        System.out.println(this.getClass() + "：" + str);
    }
}
