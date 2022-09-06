package com.hundsun.lambdas;

// lambda作用范围

import com.hundsun.interfaces.IConverter;
import com.hundsun.interfaces.IFormula;

public class IndexLambda02 {
    public static int OUTERSTATICNUM;
    int outerNum;


    public static void main(String[] args) {
        test01();
    }

    // 1. 访问局部变量
    public static void test01() {
        int num = 1;
        // 基础版
        IConverter<Integer, String> stringIConverter = from -> String.valueOf(from + num);
        String convert = stringIConverter.convert(2);
        System.out.println(convert);
        // 但是这个num是不可变值，这样改变值会报错；
        //num = 2;

        IConverter<Integer, String> stringIConverter1 = from -> {
          String convert2 =  String.valueOf(from + num);
          // 在lambda表达式内部修改也是不允许的；
          // num ++;
          return convert2;
        };
    }

    // 2. 访问成员变量和静态变量
    public void test02() {
        IConverter<Integer, String> stringIConverter = (from) -> {
          outerNum = 23;
          OUTERSTATICNUM = 33;
          return String.valueOf(from);
        };
    }

    // 3.访问默认接口方法
    public void test03() {
        IFormula formula = new IFormula() {
            @Override
            public double calculate(int a) {
                return a * a;
            }
        };
        formula.sqrt(1);
        formula.calculate(2);
        // 不能通过lambda访问默认方法
        // IFormula formulas = (a) -> sqrt(a * a);
    }
}
