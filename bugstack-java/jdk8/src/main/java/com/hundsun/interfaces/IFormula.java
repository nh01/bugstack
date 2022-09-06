package com.hundsun.interfaces;

public interface IFormula {
    public double calculate(int a);

    // default关键词必须
    default double sqrt(int a) {
        return Math.sqrt(a);
    }
}
