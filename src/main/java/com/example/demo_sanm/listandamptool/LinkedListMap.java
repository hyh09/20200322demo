package com.example.demo_sanm.listandamptool;

import java.util.LinkedList;

/**
 * @单位名称：科大国创—电信资源事业部
 * @创建人：hu.yunhui
 * @创建时间: on 2020/4/15.
 * @by: DELL)
 */
public class LinkedListMap<Key,Value> {

    // 实际存放Map元素
    LinkedList<Entry>[] tables = new LinkedList[998];
    // 实际Map大小
    private int size;

    public void put(Object key, Object value) {
        // 创建entry；
        Entry newEntry = new Entry(key, value);
        // hashCode
        int hash = getHash(key);
        // 判断是否已经在数组重存在
        LinkedList<Entry> entrylinkedList = tables[hash];

        if (entrylinkedList == null) {
            // 数组中没有存放元素
            LinkedList<Entry> linkedList = new LinkedList<>();
            linkedList.add(newEntry);
            tables[hash] = linkedList;
        } else {
            // hashCode 相同情况下 存放在链表后面
            for (Entry entry : entrylinkedList) {
                if (entry.key.equals(key)) {
                    // hashCode相同 对象也相同
                    entry.value = value;

                } else {
                    // hashCode 相同，但是对象不同。
                    entrylinkedList.add(newEntry);
                }
            }

        }
        size++;
    }

    public int  getHash(Object key) {
        int hashCode = key.hashCode();
        int hash = hashCode % tables.length;
        return hash;
    }

    public Value get(Object key) {
        return (Value) getEntry(key).value;
    }

    public Entry getEntry(Object key) {
        // hashCode
        int hash = getHash(key);
        LinkedList<Entry> listEntry = tables[hash];
        if (listEntry != null) {
            for (Entry entry : listEntry) {
                if (entry.key.equals(key)) {
                    return entry;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        LinkedListMap linkedListMap = new LinkedListMap<String, String>();
        linkedListMap.put("b", "123456");
        linkedListMap.put("b", "123");
        System.out.println(linkedListMap.get("b"));
    }


}
