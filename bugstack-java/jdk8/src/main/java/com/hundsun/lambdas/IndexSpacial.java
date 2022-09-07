package com.hundsun.lambdas;

import com.hundsun.consumer.MyConsumer;
import com.hundsun.entity.Person;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 内置函数式接口
 */
public class IndexSpacial {

    //1.Predicate断言
    //Predicate 是一个可以指定入参类型，并返回 boolean 值的函数式接口。它内部提供了一些带有默认实现的方法，
    // 可以 被用来组合一个复杂的逻辑判断（and, or, negate）：
    public static void test01() {
        Predicate<String> predicate = s -> s.length() > 0;
        boolean foo0 = predicate.test("foo");
        boolean foo1 = predicate.negate().test("foo");

        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();
    }

    //2. Functions
    // Function 函数式接口的作用是，我们可以为其提供一个原料，他给生产一个最终的产品。
    // 通过它提供的默认方法，组合,链行处理(compose, andThen)：
    public static void test02() {
        Function<String, Integer> toInteger = Integer::valueOf;

        Function<String, String> backToString = toInteger.andThen(String::valueOf);

        Function<String, String> afterToStartWith = backToString.andThen(IndexLambda::startsWith);

        String apply = afterToStartWith.apply("123");
        System.out.println(apply);
    }

    //3. Suppliers
    // Supplier 与 Function 不同，它不接受入参，直接为我们生产一个指定的结果，有点像生产者模式：
    public static void test04() {
        Supplier<Person> personSupplier = Person::new;
        personSupplier.get();

        Supplier<String> stringSupplier = IndexSpacial::doSomething;
        stringSupplier.get();

        Supplier<String> stringSupplier1 = new IndexSpacial()::doSomething1;
        stringSupplier.get();
    }

    //4.Consumers
    // 对于 Consumer，我们需要提供入参，用来被消费，如下面这段示例代码
    public static void test05() {
        Consumer<Person> greeter01 = new Consumer<Person>() {
            @Override
            public void accept(Person person) {
                System.out.println("Hello, " + person.getLastName());
            }
        };

        Consumer<Person> greeter02 = (p) -> System.out.println("Hello, " + p.getFirstName());
        greeter02.accept(new Person("Luke", "Skywalker"));

        Consumer<Person> greeter03 = new MyConsumer<Person>()::accept;
        greeter03.accept(new Person("Luke", "Skywalker"));
    }

    //5.Comparators
    // Comparator 在 Java 8 之前是使用比较普遍的。Java 8 中除了将其升级成了函数式接口，还为它拓展了一些默认方法：
    public static void test06() {
        Comparator<Person> comparator = (p1, p2) -> p2.getFirstName().compareTo(p1.getFirstName());
        Comparator<Person> comparator1 = Comparator.comparing(p -> p.getFirstName());

        Person p1 = new Person("John", "Doe");
        Person p2 = new Person("Alice", "Wonderland");
        comparator.compare(p1, p2);
        comparator1.reversed().compare(p1, p2);
    }

    //6.Optionals
    //Optional 它不是一个函数式接口，设计它的目的是为了防止空指针异常（NullPointerException)
    // Optional 看做是包装对象（可能是 null, 也有可能非 null）的容器。
    // 当你定义了一个方法，这个方法返回的对象可能是空，也有可能非空的时候，你就可以考虑用 Optional 来包装它，这也是在 Java 8 被推荐使用的做法
    public static void test07() {
        Optional<String> optional = Optional.of("bam");
        optional.isPresent();
        optional.get();
        optional.orElse("fallback");
        optional.ifPresent(s -> System.out.println(s.charAt(0)));
        Optional<Person> optionalPerson = Optional.of(new Person());
        optionalPerson.ifPresent(s -> System.out.println(s.getFirstName()));
    }

    //7.Stream流
    // 一个包含一个或多个元素的集合做各种操作。这些操作可能是 中间操作 亦或是 终端操作。 终端操作会返回一个结果，而中间操作会返回一个 Stream 流
    // 你只能对实现了 java.util.Collection 接口的类做流的操作
    // Stream 流支持同步执行，也支持并发执行
    //Map不支持Stream流，但是他的key和value是支持的
    public static void test08() {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd");
        //1.过滤器 filter
        // Filter 的入参是一个 Predicate, 上面已经说到，Predicate 是一个断言的中间操作，它能够帮我们筛选出我们需要的集合元素。
        // 它的返参同样 是一个 Stream 流，我们可以通过 foreach 终端操作，来打印被筛选的元素
        list.stream().filter(s -> s.startsWith("a")).forEach(System.out::println);
        //2.排序 sorted
        //Sorted 同样是一个中间操作，它的返参是一个 Stream 流。
        // 另外，我们可以传入一个 Comparator 用来自定义排序，如果不传，则使用默认的排序规则。
        list.stream().sorted().filter(s -> s.startsWith("a")).forEach(System.out::println);
        // 注意；这个sorted 只是做了一个排序的视图进行输出，实际没有将List内的数据进行排序
        System.out.println(list.toString());
        //3.Map 转换
        // 中间操作映射通过给定的函数将每个元素转换为另一个对象。例如下面的示例，通过 map 我们将每一个 string 转成大写：
        list.stream().map(String::toUpperCase).sorted(Comparator.reverseOrder()).forEach(System.out::println);
        //4.match匹配
        // match 用来做匹配操作，它的返回值是一个 boolean 类型。通过 match, 我们可以方便的验证一个 list 中是否存在某个类型的元素
        boolean anyMach = list.stream().anyMatch(s->s.startsWith("a"));
        boolean allMach = list.stream().allMatch(s -> s.startsWith("a"));
        boolean noMach = list.stream().noneMatch(s -> s.startsWith("a"));
        //5.count计数
        // count 是一个终端操作，它能够统计 stream 流中的元素总数，返回值是 long 类型。
        long count = list.stream().filter(s -> s.startsWith("a")).count();
        // 6.Reduce
        //Reduce 中文翻译为：减少、缩小。通过入参的 Function，我们能够将 list 归约成一个值。它的返回类型是 Optional 类型。
        Optional<String> reduce = list.stream().sorted().reduce((s1, s2) -> s1 + "#" + s2);
        reduce.ifPresent(System.out::println);
    }




    public static String doSomething() {
        return "success";
    }

    public String doSomething1() {
        return "success";
    }


    public static void main(String[] args) {
        test02();
    }
}
