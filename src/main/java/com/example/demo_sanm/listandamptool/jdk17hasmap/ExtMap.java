package com.example.demo_sanm.listandamptool.jdk17hasmap;

/**
 * @单位名称：科大国创—电信资源事业部
 * @创建人：hu.yunhui
 * @创建时间: on 2020/4/16.
 * @by: DELL)
 *
 *
 * 1.HahsMap 1.7 数组 + (单链表) 链表
 *
 * 单链表： 不保存上一个节点 (只保存下一个节点 next)
 *
 * 思考：  HashMap 底层为什么不采用双链表？
 *
 * hash碰撞 (hash冲突问题)
 *   hashCode
 *
 *纯手写Map
 *
 */
public interface ExtMap<K,V> {

    // 向集合中插入数据
    public V put(K k, V v);

    // 根据k 从Map集合中查询元素
    public V get(K k);

    // 获取集合元素个数
    public int size();



    interface Entry<K, V> {
        K getKey();

        V getValue();

        V setValue(V value);
    }

}
