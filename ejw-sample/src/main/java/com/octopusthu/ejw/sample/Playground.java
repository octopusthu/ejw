package com.octopusthu.ejw.sample;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class Playground {

    public static void main(String[] args) throws Exception {
//        floatPrimitiveTest();
//        floatWrapperTest();
//        switchTest();
//        bigDecimalTest();
//        lockTest();
//        uriTest();
        System.out.println(UUID.randomUUID());
    }

    public static void uriTest() {
        List<String> data = new ArrayList<>();
        data.add("");
        data.add("a");
        data.add("http://127.0.0.1:8080/portal/#/user/login");
        data.add("http://127.0.0.1:8080/ipms/urms/showMenu?sysName=%E4%BA%92%E8%81%94%E7%BD%91%E4%BF%A1%E6%81%AF%E9%87%87%E9%9B%86%E5%8F%8A%E8%88%86%E6%83%85%E7%9B%91%E6%B5%8B%E7%B3%BB%E7%BB%9F");
        data.add("http://127.0.0.1:8080/ipaps/acceptToken?token=");
        data.add("http://127.0.0.1:8080/portal/api/sso/login?service=http://127.0.0.1:8080/ipaps/acceptToken?token=");


        List<URI> uriList = new ArrayList<>();
        for (String s : data) {
            try {
                uriList.add(new URI(s));
            } catch (URISyntaxException e) {
                log.warn("Invalid URI: '" + s + "'");
            }
        }

        List<URL> urlList = new ArrayList<>();
        for (String s : data) {
            try {
                urlList.add(new URL(s));
            } catch (MalformedURLException e) {
                log.warn("Invalid URL: '" + s + "'");
            }
        }

        System.out.println("Done.");
    }

    public static void floatPrimitiveTest() {
        float a = 1.0f - 0.9f;
        float b = 0.9f - 0.8f;
        System.out.println(a == b);
    }

    public static void floatWrapperTest() {
        Float a = Float.valueOf(1.0f - 0.9f);
        Float b = Float.valueOf(0.9f - 0.8f);
        System.out.println(a.equals(b));
    }

    public static void switchTest() {
        String param = null;
        switch (param) {
            case "null":
                System.out.println("null");
                break;
            default:
                System.out.println("default");
        }
    }

    public static void bigDecimalTest() {
        BigDecimal a = new BigDecimal(0.1);
        System.out.println(a);
        BigDecimal b = new BigDecimal("0.1");
        System.out.println(b);
    }

    public static void lockTest() {
        Lock lock = new ReentrantLock();
        try {
            lock.tryLock();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
