package com.aq.test;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @ClassName FileRead
 * @Description :
 * @Date 2020/3/13 20:45
 **/
public class FileRead {

    public static void main(String[] args) {

//        List<String> strings = Arrays.asList("a", "cba", "c", "cdc", "e", "fc");
        //过滤，过滤出以 c 为前缀的字符串  转换成大写  / 排序  / for 循环打印
//        strings.stream().filter(s->s.startsWith("c")).map(String::toUpperCase).sorted().forEach(System.out::println);

        readText();

    }

    public static void readText() {
        List<String> arrIdfa = new ArrayList<>();
        List<String> mapIdfaUserIp = new ArrayList<>();

        Map<String, List<String>> result = new HashMap<>();

        /* 读取数据 */
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("F:\\my-project\\leetCodeProject\\src\\main\\webapp\\file\\idfa.txt")),
                    "UTF-8"));
            String lineTxt = null;
            while ((lineTxt = br.readLine()) != null) {
                arrIdfa.add(lineTxt);
            }
            br.close();
        } catch (Exception e) {
            System.err.println("read errors :" + e);
        }

        /* 读取数据 */
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("F:\\my-project\\leetCodeProject\\src\\main\\webapp\\file\\log-12-5.txt")),
                    "UTF-8"));
            String lineTxt = null;
            while ((lineTxt = br.readLine()) != null) {
                String[] names = lineTxt.split(",");
                String userIp = "";
                for (String name : names) {
                    if (name.indexOf("userIp")!=-1) {
                        userIp = name.substring(7, name.length());
                    }
                }
                int idfaIndex = lineTxt.indexOf("idfa");
                int platformIndex = lineTxt.indexOf("platform");
                String idfa = lineTxt.substring(idfaIndex + 5, platformIndex - 1);

                mapIdfaUserIp.add(idfa+","+userIp);
            }
            br.close();
        } catch (Exception e) {
            System.err.println("read errors :" + e);
        }

        for (String idfa:arrIdfa) {


            ArrayList<String> resultArr = new ArrayList<>();

            for (String idfaUserIp:mapIdfaUserIp) {
                String[] split = idfaUserIp.split(",");

                if(idfa.equals(split[0])){
                    resultArr.add(split[1]);
                }

            }

            if (resultArr!=null && resultArr.size()>0) {
                result.put(idfa,resultArr);
            }
//            result.put(idfa,resultArr);
        }

        System.out.println(JSON.toJSONString(result));
    }
}
