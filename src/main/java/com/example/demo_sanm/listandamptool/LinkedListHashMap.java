package com.example.demo_sanm.listandamptool;

import org.junit.Test;

/**
 * @单位名称：科大国创—电信资源事业部
 * @创建人：hu.yunhui
 * @创建时间: on 2020/4/14.
 * @by: DELL)
 *
 * 基于LinkedList 实现HashMap
 */
public class LinkedListHashMap {


    /**
     *
     */

    //
    Object[] tables =  new  Object[998];

    public  void put(Object key,Object value){
       int hashcode = key.hashCode();
       System.out.println("====hashcode:"+hashcode);
        int hash=hashcode % tables.length;
        tables[hash]=value;
        System.out.println("存放值成功:"+tables[hash]+"下标:"+hash);
    }


    public Object get(Object key){
        int hashcode = key.hashCode();
        int hash=hashcode % tables.length;
       return tables[hash];
    }



    @Test
    public  void Test1(){
         String  a = new  String("111");
        String  c = new  String("111");

        String b="111";
         System.out.println("===>"+a==c);
//        LinkedListHashMap linkedListHashMap= new LinkedListHashMap();
//        linkedListHashMap.put("通话","aaaa");
//        linkedListHashMap.put("重地","bbbb");
//        System.out.println("====>"+linkedListHashMap.get("通话"));
//        System.out.println("====>"+linkedListHashMap.get("重地"));
    }


    /**
     * 存在bug:
     *     通话      hashcode  1179395
     *     重地
     *
     */


}


