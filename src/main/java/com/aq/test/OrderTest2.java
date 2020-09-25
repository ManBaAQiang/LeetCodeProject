package com.aq.test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName OrderTest2
 * @Description :
 * @Date 2020/7/9 14:18
 * @Version 1.0
 **/
public class OrderTest2 {
    public static void main(String[] args) {
        ArrayList<CommodityDetail> commodityList = new ArrayList<>();
        commodityList.add(new CommodityDetail(Commodity.BEER,3));
        commodityList.add(new Noodles(Commodity.NOODLES,3));
        commodityList.add(new CommodityDetail(Commodity.SWATER,2));

        BigDecimal sumPriseByMei = getSum(commodityList).setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println("顾客小美:矿泉水2瓶，方便面3包，啤酒3罐；总消费:"+sumPriseByMei.toString()+"元");

        ArrayList<CommodityDetail> commodityList2 = new ArrayList<>();
        commodityList2.add(new CommodityDetail(Commodity.BEER,2));
        commodityList2.add(new Noodles(Commodity.NOODLES,5));
        BigDecimal sumPriseByMing = getSum(commodityList2).setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println("顾客小明:啤酒2罐，方便面5包；总消费:"+sumPriseByMing.toString()+"元");

    }

    public static BigDecimal getSum(List<CommodityDetail> commodityLIst) {
        BigDecimal priceSum = new BigDecimal(0);
        for (CommodityDetail commodity : commodityLIst) {
            BigDecimal sumPrise = commodity.getSumPrise();
            priceSum = priceSum.add(sumPrise, MathContext.DECIMAL32);
        }
        return priceSum;
    }
}

class CommodityDetail{

    private Commodity commodity;

    private Integer count;

    public CommodityDetail(Commodity commodity, Integer count) {
        this.commodity = commodity;
        this.count = count;
    }

    public BigDecimal getSumPrise() {
        BigDecimal sumItem = new BigDecimal(0);
        if(getCount()>2) {
            sumItem = BigDecimal.valueOf(getCommodity().getPrice()).multiply(new BigDecimal(getCount())).multiply(new BigDecimal(0.9));
        }else {
            sumItem =  BigDecimal.valueOf(getCommodity().getPrice()).multiply(new BigDecimal(getCount()));
        }
        return sumItem;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }
}

class Noodles extends CommodityDetail{


    public Noodles(Commodity commodity, Integer count) {
        super(commodity, count);
    }

    public BigDecimal getSumPrise() {
        BigDecimal sumItem = new BigDecimal(0);
        if(getCount()>2) {
            sumItem = BigDecimal.valueOf(getCommodity().getPrice()).multiply(new BigDecimal(getCount())).multiply(new BigDecimal(0.9));
        }else {
            sumItem = BigDecimal.valueOf(getCommodity().getPrice()).multiply(new BigDecimal(getCount()));
        }
        if(sumItem.compareTo(new BigDecimal(20))==1) {
            return sumItem.subtract(new BigDecimal(2));
        }else {
            return sumItem;
        }
    }
}