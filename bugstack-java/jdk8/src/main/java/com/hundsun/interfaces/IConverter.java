package com.hundsun.interfaces;


@FunctionalInterface
public interface IConverter<F, T> {
    T convert(F from);
}
