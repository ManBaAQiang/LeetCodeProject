package com.aq.test;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName OrderTest
 * @Description :
 * @Date 2020/7/9 14:18
 * @Version 1.0
 **/
public class OrderTest implements Serializable  {
    public static void main(String[] args) throws ClassNotFoundException {
        BigDecimal sumPriseByMing = getSum(Commodity.SWATER, 2).add(getSum(Commodity.BREAD, 1)).add(getSum(Commodity.KOLA, 1));
        System.out.println("顾客小明:矿泉水2瓶，面包1个，瓶装可乐1个；总消费:"+sumPriseByMing.toString()+"元");

        BigDecimal sumPriseByMei = getSum(Commodity.BINGUN, 3).add(getSum(Commodity.NOODLES, 1)).add(getSum(Commodity.KOLA, 2));
        System.out.println("顾客小美:冰棍3个，瓶装可乐2个，方便面1包；总消费:"+sumPriseByMei.toString()+"元");

        Class<?> orderTest2 = Class.forName("com.aq.test.OrderTest");
        System.out.println(orderTest2);
    }

    public static BigDecimal getSum(Commodity commodity, Integer n) {
        return BigDecimal.valueOf(commodity.getPrice()).multiply(new BigDecimal(n));
    }
}

enum Commodity {
    BINGUN("冰棍", 1.5),
    SWATER("矿泉水", 2.0),
    BREAD("面包", 2.5),
    NOODLES("方便面", 4.5),
    KOLA("瓶装可乐", 3.5),
    BEER("啤酒", 3.5);

    private String name;

    private Double price;

    private Commodity(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }
}