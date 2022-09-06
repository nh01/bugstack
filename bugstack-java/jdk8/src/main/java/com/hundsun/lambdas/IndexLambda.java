package com.hundsun.lambdas;

import com.hundsun.entity.Person;
import com.hundsun.interfaces.IConverter;
import com.hundsun.interfaces.IPersonFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * lambda表达式
 */
public class IndexLambda {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("peter", "anna", "mike", "xenia");
        Collections.sort(list, (String a, String b) -> a.compareTo(b));
        list.sort((a, b) -> b.compareTo(a));
        list.sort(Comparator.reverseOrder());
    }

    // 函数式接口
    public void test01() {
        // 1. 传统方式实现
        IConverter<String, Integer> converter01 = new IConverter<String, Integer>() {
            @Override
            public Integer convert(String from) {
                return Integer.valueOf(from);
            }
        };
        System.out.println(converter01.convert("123"));

        // 2.简化版
        IConverter<String, Integer> converter02 = (from) -> {
          return Integer.valueOf(from);
        };

        // 3. 精简版
        IConverter<String, Integer> converter03 = (from) -> Integer.valueOf(from);

        // 4.终极版
        IConverter<String, Integer> converter04 = Integer::valueOf;
    }

    // 方法和构造函数的便捷应用 ::
    public static void test02() {
        IConverter<String, String> converter = s -> String.valueOf(s.charAt(0));

        IConverter<String,String> converter1 = IndexLambda::startsWith;
    }

    public static void test03() {
        IPersonFactory<Person> personIPersonFactory = Person::new;
        Person person = personIPersonFactory.create("Peter", "Parker");
    }

    // 方法案例，获取字符传首个字符
    public static String startsWith(String s) {
        return String.valueOf(s.charAt(0));
    }
}
