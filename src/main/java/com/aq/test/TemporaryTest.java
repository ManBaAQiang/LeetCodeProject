package com.aq.test;

/**
 * @ClassName TemporaryTest
 * @Description :
 * @Author YaoAqiang
 * @Date 2020/7/17 16:24
 * @Version 1.0
 **/
public class TemporaryTest {

    public static void main(String[] args) {

        String str = "Mozilla/5.0 (Linux; Android 10; TAS-AN00 Build/HUAWEIThonorAS-AN00; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/67.0.3396.87 XWEB/1169 MMWEBSDK/200201 Mobile Safari/537.36 MMWEBID/5128 MicroMessenger/7.0.12.1600(0x27000C33) Process/tools NetType/4G Language/zh_CN ABI/arm64";
        String s = str.toLowerCase();
        boolean huawei = s.matches(".*(huawei|honor).*");
        System.out.println(huawei);

    }

}
