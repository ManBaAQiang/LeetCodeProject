package com.aq.streamTest;

import java.util.Stack;
import java.util.stream.Stream;

/**
 * @ClassName MapTest
 * @Description :
 * @Author YaoAqiang
 * @Date 2020/1/18 16:45
 * @Version 1.0
 **/
public class MapTest {

    public static void main(String[] args) {
//        map01();
//        lambdaTest();
//        stackTest();

        String item = "{rer:ewqeweqeqeqwe,fwfew:dqedqedeq,list:[eef,fe,e,dw,d,ww,sw,dwd,w]}";
        String expertsNum = "{312112:23,434343:43432,4332:4332}";
        expertsNum = new StringBuilder(",expertsNumMap:").append(expertsNum).toString();

        StringBuilder result = new StringBuilder(item);
        String resultStr = result.insert(item.length() - 1, expertsNum).toString();
        System.out.println(resultStr);
    }

    private static void stackTest() {
        Stack<Integer> strings = new Stack<>();
        strings.push(11);
        strings.push(23);
        strings.push(13);
        strings.push(74);
        strings.push(55);
        System.out.println(strings.push(100));
        System.out.println(strings.pop());  //移除栈顶元素并返回数据
        System.out.println(strings.peek());  //查看栈顶元素，但是不移除它
        System.out.println(strings.search(74));
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