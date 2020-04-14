package com.example.demo_sanm.listandamptool;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @单位名称：科大国创—电信资源事业部
 * @创建人：hu.yunhui
 * @创建时间: on 2020/4/14.
 * @by: DELL)
 *
 * 1.基于ArrayList
 *
 * 基于ArrayList 实现HashMap集合
 */
public class ExtArraylistHashMap<Key,Value> {

    //1.Map集合存储的容量
    private List<Entry<Key,Value>>  tables = new ArrayList <Entry<Key,Value>>();
    //map容器的实际容量
    private  int size=tables.size();

    /**
     *
     * key , value
     */
    public  void put(Key key,Value value){
      //  Entry  entry = new Entry(key,value);
        Entry  entry=getEntry(key);
        //2.调用put的时候,将hash 存储对象存入到ArrayList中
        if(entry !=null) {
            /**已经存在*/
            entry.value=value;
        }else {
            Entry  entrynew = new Entry(key,value);
            tables.add(entrynew);

        }

    }


    public  Value  get(Key key){
        Entry<Key,Value> entry=  getEntry(key);
         return entry==null?null:entry.value;
    }


    public  Entry<Key,Value>  getEntry(Key key){
        //从头查到尾做优化#####准备优化
        for(Entry<Key,Value> entry:tables){
            if(entry.key.equals(key)){
                return  entry;
            }
        }
        return  null;
    }


    @Test
    public  void Test1(){
        ExtArraylistHashMap   hashMap = new ExtArraylistHashMap<String,String>();
        hashMap.put("a","aaaa");
        hashMap.put("b","bbbb");
        hashMap.put("a","44444");

        System.out.println("hasmp: "+hashMap.get("a"));
        System.out.println("hasmp:  "+hashMap.get("c"));

        System.out.println("hasmp:  "+hashMap);


    }
}


//hash存储对象
class  Entry<Key,Value>{
    //hashMap集合存储的key
    Key  key;

    //hashMap集合存储的value
    Value  value;

    public Entry(Key key, Value value) {
        super();
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Entry{");
        sb.append("key=").append(key);
        sb.append(", value=").append(value);
        sb.append('}');
        return sb.toString();
    }
}











