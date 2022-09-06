package com.hundsun.interfaces;

import com.hundsun.entity.Person;

@FunctionalInterface
public interface IPersonFactory<P extends Person> {
    P create(String firstName, String lastName);
}
