package com.aq.java8Test;

/**
 * @ClassName FunctionalInterfaceTest
 * @Description :
 * @Date 2020/3/11 22:57
 **/
public class FunctionalInterfaceTest {

    public static void main(String[] args) {

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

}
