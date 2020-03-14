package com.aq.java8Test;

/**
 * @Description //函数式接口，有且仅有一个抽象方法，但是可以有多个默认方法，这是java8之后的新特性
 * @Date 22:53 2020/3/11
 **/
@FunctionalInterface
interface Converter<T, F> {

    default double sprt(int a) {
        return Math.sqrt(a);
    }

    F convent(T from);

}
