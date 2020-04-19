package com.example.demo_sanm.designpattern.factoryPattern.imp;

import com.example.demo_sanm.designpattern.factoryPattern.Computer;

/**
 * @单位名称：科大国创—电信资源事业部
 * @创建人：hu.yunhui
 * @创建时间: on 2020/4/19.
 * @by: DELL)
 */
public class ComputerFactory {

    /**
     *
     */
    public Computer  createComputter(String type){
        if(type == null  ||  type.equals("")){
            return  null;
        }
        switch (type){
            case "ASUS" :
                  return   new ASUS();
            case  "Lenovo":
                  return  new Lenovo();
            case  "MacBook":
                  return  new MacBook();
             default:
                  return  null;

        }
    }
}
