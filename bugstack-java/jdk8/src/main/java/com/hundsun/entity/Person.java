package com.hundsun.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Person {
    String firstName;
    String lastName;

    public Person() {}

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
