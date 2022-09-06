package com.hundsun.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BeanA {
    int index;
    String name;
    int age;
    String userId;

    public BeanA(int index, String name, int age, String userId) {
        this.index = index;
        this.name = name;
        this.age = age;
        this.userId = userId;
    }
}
