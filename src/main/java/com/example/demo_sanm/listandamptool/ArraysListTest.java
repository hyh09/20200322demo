package com.example.demo_sanm.listandamptool;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @单位名称：科大国创—电信资源事业部
 * @创建人：hu.yunhui
 * @创建时间: on 2020/4/15.
 * @by: DELL)
 */
public class ArraysListTest {


    @Test
    public  void Test1(){
        List<String> list1= new ArrayList <>();
        list1.add("111");

        List<String> list2= new ArrayList <>();
        list2.addAll(list1);


        for(int i=0;i<3;i++){
            list2.add("===>"+i);

        }
        list2.add(1,"44");

        System.out.println("===>"+list1);
        System.out.println("===>"+list2);
    }
}
