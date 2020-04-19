package com.example.demo_sanm.designpattern.factoryPattern.imp;

import com.example.demo_sanm.designpattern.factoryPattern.Computer;

/**
 * @单位名称：科大国创—电信资源事业部
 * @创建人：hu.yunhui
 * @创建时间: on 2020/4/19.
 * @by: DELL)
 */
public class ComputeFacotyDemo {

    public  static  void  main(String[] args){
        ComputerFactory  factory = new ComputerFactory();
        //创建Asus
        Computer asus =factory.createComputter("ASUS");
        asus.make();


        //lenovo
        Computer  lenovo = factory.createComputter("Lenovo");
        lenovo.make();


        //MacBook
        Computer macbook = factory.createComputter("MacBook");
        macbook.make();


    }
}
