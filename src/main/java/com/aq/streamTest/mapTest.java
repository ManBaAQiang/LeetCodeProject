package com.aq.streamTest;

import java.util.stream.Stream;

/**
 * @ClassName mapTest
 * @Description :
 * @Author YaoAqiang
 * @Date 2020/1/18 16:45
 * @Version 1.0
 **/
public class mapTest {

    public static void main(String[] args) {
//        map01();
        lambdaTest();

    }

    public static void map01() {
        Stream.of("apple","banana","orange","waltermaleon","grape").map(e -> e.length()).forEach(System.out::println);
    }

    public static void lambdaTest() {
        Action action = content -> System.out.println(content);
        action.execute("lambdaTest");
    }

}

interface Action {
    void execute(String content);
}