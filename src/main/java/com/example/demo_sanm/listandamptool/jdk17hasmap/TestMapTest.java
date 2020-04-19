package com.example.demo_sanm.listandamptool.jdk17hasmap;

import org.junit.Test;

/**
 * @单位名称：科大国创—电信资源事业部
 * @创建人：hu.yunhui
 * @创建时间: on 2020/4/17.
 * @by: DELL)
 */
public class TestMapTest {

    @Test
    public  void Test1(){



        ExtHashMap  map = new ExtHashMap();
        for(int i=0;i<11;i++){
            map.put("key+"+i,"vale:"+i);
        }

        map.print();




    }
}
