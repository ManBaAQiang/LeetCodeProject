package com.aq.java8Test;

import java.util.Optional;

/**
 * @ClassName FunctionalInterfaceTest
 * @Description :
 * @Date 2020/3/11 22:57
 **/
public class FunctionalInterfaceTest {

    public static void main(String[] args) {

        test1();
        //test2();

    }

    private static void test2() {
        Converter<String, Integer> converter1 = new Converter<String, Integer>() {
            @Override
            public Integer convent(String from) {
                return Integer.valueOf(from);
            }
        };

        //lambda表达式
        Converter<String, Integer> converter = from -> Integer.valueOf(from);
        Integer convent = converter.convent("123");
        System.out.println(convent);
    }

    public static void test1() {
        Inner inner = new Inner();
        inner.foo = "aaaaa";
        Nested nested = new Nested();
        nested.inner = inner;
        Outer outer = new Outer();
        outer.nested = nested;

        //空对象检测
        Optional.of(outer).map(Outer::getNested).map(Nested::getInner).map(Inner::getFoo).ifPresent(System.out::println);
        Optional<String> s = Optional.of(outer).map(Outer::getNested).map(Nested::getInner).map(Inner::getFoo);
        System.out.println(s.isPresent());

    }

}

// 最外层对象
class Outer {
    Nested nested;
    Nested getNested() {
        return nested;
    }
}
// 第二层对象
class Nested {
    Inner inner;
    Inner getInner() {
        return inner;
    }
}
// 最底层对象
class Inner {
    String foo;
    String getFoo() {
        return foo;
    }
}
