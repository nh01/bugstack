package com.hundsun.lambdas;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 并行流
 * 流可以是顺序的，也可以是并行的。顺序流上的操作在单个线程上执行，而并行流上的操作在多个线程上并发执行。
 *
 *
 */
public class IndexParallelStreams {
    public static List<String> buildList() {
        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }
        return values;
    }

    // 下面的示例演示了使用并行流来提高性能是多么的容易。亲测提升了1倍性能！
    //1. Sequential Sort 顺序流排序
    public static void test01() {
        List<String> list = buildList();
        long t0 = System.nanoTime();
        long count = list.stream().sorted().count();
        System.out.println(count);
        long t1 = System.nanoTime();
        // 纳秒转微秒
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("顺序流排序耗时: %d ms", millis)); // 909 ms
    }
    // 2.Parallel Sort 并行流排序
    public static void test02() {
        List<String> list = buildList();
        long t0 = System.nanoTime();
        long count = list.parallelStream().sorted().count();
        System.out.println(count);
        long t1 = System.nanoTime();
        // 纳秒转微秒
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("顺序流排序耗时: %d ms", millis)); // 463 ms
    }

    public static void main(String[] args) {
        test02();
    }
}
