package com.example.demo_sanm.testyun;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @单位名称：科大国创—电信资源事业部
 * @创建人：hu.yunhui
 * @创建时间: on 2020/4/12.
 * @by: DELL)
 *
 *
 *验证Stringbuffer 与StringBuilder的区别：线程安全与非线程安全。
 */

public class testStringBuilderAndStringBuffer {


    /**
     * String time:571
     StringBuffer time:3
     StringBuilder time:1

     * 验证执行效率
     *  StringBuilder 执行速度更快
     *  StringBuffer  执行速度慢
     *  String 最慢  String底层是一个final类型的字符数组，所以String的值是不可变的，每次对String的操作都会生成新的String对象，造成内存浪费
     * @param args
     */
    public static void main(String[] args) {
        String();
        StringBuffer();
        StringBuilder();
    }
    private static void String(){
        long start=System.currentTimeMillis();
        String str="a";
        for (int i = 0; i < 10000; i++) {
            str+=i;
        }
        long stop=System.currentTimeMillis();
        System.out.println("String time:"+(stop-start));
    }
    private static void StringBuffer(){
        long start=System.currentTimeMillis();
        StringBuffer str=new StringBuffer("a");
        for (int i = 0; i < 10000; i++) {
            str.append(i);
        }
        long stop=System.currentTimeMillis();
        System.out.println("StringBuffer time:"+(stop-start));
    }
    private static void StringBuilder(){
        long start=System.currentTimeMillis();
        StringBuilder str=new StringBuilder("a");
        for (int i = 0; i < 10000; i++) {
            str.append(i);
        }
        long stop=System.currentTimeMillis();
        System.out.println("StringBuilder time:"+(stop-start));
    }









    @Test
    public void  Text() {
        long a=100000;
        {
            List<Thread>li=new ArrayList<Thread>();
            StringBuffer sb=new StringBuffer("a");
            for (int i = 0; i < a; i++) {
                Thread tr=new Thread(){
                    @Override
                    public void run() {
                        sb.append(".");
                    }
                };
                tr.start();
                li.add(tr);
            }
            for (Thread item:li) {
                try {
                    item.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("lengthOne="+sb.length());
        }
        //===================================
        {
            List<Thread> li=new ArrayList<Thread>();
            StringBuilder sb=new StringBuilder("a");
            for (int i = 0; i < a; i++) {
                Thread tr=new Thread(){
                    @Override
                    public void run() {
                        sb.append(".");
                    }
                };
                tr.start();
                li.add(tr);
            }
//            for (Thread item:li) {
//                try {
//                    item.join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
            System.out.println("lengthTne="+ sb.length());
        }
    }

}

