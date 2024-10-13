package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;

import org.hibernate.validator.internal.util.stereotypes.Immutable;
import org.springframework.boot.autoconfigure.domain.EntityScan;
@EntityScan
@Table(name="namedata")
public class NameData {

    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = Generated.AUTO)
    @Column
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(nullable = true)
    private Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}