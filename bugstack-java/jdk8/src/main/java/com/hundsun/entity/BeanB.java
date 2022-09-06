package com.hundsun.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BeanB {
    int index;
    String name;
    int age;
    String userId;

    public BeanB(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
