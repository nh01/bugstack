package com.hundsun.consumer;

import com.hundsun.entity.Person;

public class MyConsumer<T> {
    public String accept(Person person){
        // can do everything
        return "success";
    }
}
