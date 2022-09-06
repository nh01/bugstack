package com.hundsun.lambdas;

import com.hundsun.entity.BeanA;
import com.hundsun.entity.BeanB;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Map是不支持 Stream 流的，因为 Map 接口并没有像 Collection 接口那样，定义了 stream() 方法。
 * 但是，我们可以对其 key, values, entry 使用 流操作，
 * 如 map.keySet().stream(), map.values().stream() 和 map.entrySet().stream().
 */
public class IndexMap {

    // 简单标准遍历
    public static void test01() {
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put(i, "val" + i);
        }
        // foreach遍历
        map.forEach((key, value) -> System.out.println(value));
    }

    //做一个Map对象的转换输出；（定义两个类BeanA、BeanB）
    public static void test02() {
        Map<Integer, BeanA> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            // 与老版不同的是，putIfAbent() 方法在 put 之前，  不用在写if null continue了
            // 会判断 key 是否已经存在，存在则直接返回 value, 否则 put, 再返回 value
            map.putIfAbsent(i, new BeanA(i, "明明" + i, i + 20, "89021839021830912809" + i));
        }

        Stream<BeanB> beanBStream = map.values().stream().map(new Function<BeanA, BeanB>() {
            @Override
            public BeanB apply(BeanA beanA) {
                return new BeanB(beanA.getName(), beanA.getAge());
            }
        });
        Stream<BeanB> beanBStream1 = map.values().stream().map(beanA -> new BeanB(beanA.getName(), beanA.getAge()));
        beanBStream1.forEach(System.out::println);
    }

    // 除了上面的 putIfAbsent() 和 forEach() 外，我们还可以很方便地对某个 key 的值做相关操作：
    public static void test03() {
        Map<Integer, String> map = new HashMap<>();
        // 如下：对 key 为 3 的值，内部会先判断值是否存在，存在，则做 value + key 的拼接操作
        map.computeIfPresent(3, (num, value) -> value + num);
        map.get(3); // val33

        // 先判断key=9的元素是否存在，存在则删除
        map.computeIfPresent(9, (num, val) -> null);
        map.containsKey(9);

        //computerIfAbsent(),当key不存在时才会做相关处理
        map.computeIfAbsent(23, num -> "val" + num);
        map.get(23);

        // computerIfAbsent() 判断key是否存在，存在则不做任何处理
        map.computeIfAbsent(3, num -> "bam");
        map.get(3);
    }

    // 删除 只有当给定的 key 和 value 完全匹配时，才会执行删除操作。
    public static void test04() {
        Map<Integer, String> map = new HashMap<>();
        map.remove(3, "val3");
        map.get(3);// val33

        map.remove(3, "val33");
        map.get(3); // null
    }

    // JDK 8 中提供了带有默认值的 getOrDefault() 方法：
    // 对于 value 的合并操作也变得更加简单：
    public static void test05() {
        Map<Integer, String> map = new HashMap<>();
        map.getOrDefault(42, "not found");

        // merge方法 先判断进行合并的key是否存在，存在则会添加元素
        map.merge(9, "val9", (value, newValue) -> value.concat(newValue));
        map.get(9);   //  val9

        map.merge(9, "concat", (value, newValue) -> value.concat(newValue));
        map.get(9);   //  val9concat
    }
}
