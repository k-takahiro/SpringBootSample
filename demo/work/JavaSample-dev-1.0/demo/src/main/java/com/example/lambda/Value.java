package com.example.lambda;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Value implements Comparable<Value> {
    private int index;
    private String value;

    public Value(int index, String value) {
        this.index = index;
        this.value = value;
    }

    @Override
    public String toString() {
        //return "Value [index=" + index + ", value=" + value + "]";
        return ToStringBuilder.reflectionToString(this);
    }

    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int compareTo(Value o) {
        return o.index - this.index;
    }
    
}
