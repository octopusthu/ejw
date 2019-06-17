package com.octopusthu.ejw.sample;

import java.math.BigDecimal;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Playground {


    public static void main(String[] args) {
//        floatPrimitiveTest();
//        floatWrapperTest();
//        switchTest();
//        bigDecimalTest();
        lockTest();
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
