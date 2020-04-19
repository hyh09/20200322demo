package com.example.demo_sanm.testyun;

import java.util.Map;
import java.util.Scanner;

/**
 * @单位名称：科大国创—电信资源事业部
 * @创建人：hu.yunhui
 * @创建时间: on 2020/4/19.
 * @by: DELL)
 */
public class ShuiTest {


    /**
     *
     * /2. 可扩展性，考虑区间的变化，
     /比如说起征点从5000变成10000等等，或者说85000以上的征税50
     /这里举个例子，比如说税前10000元，
     /5000部分是不扣税，后面5000，3000扣税3%，2000扣税10%
     * @param args
     */





    /**
     *
     * @param i
     * @param num 起征点
     * @param  num2     超过8500
     */
    public  void printil(double i,double num,int num2){

        double s=i-num;
        double j=0;
        if(s>0){
            //5000-8000
            if(s<=3000){
                j=s*0.03;
             //8001-17000
            }else if(s<=12000){
                j=s*0.1;
                //17001-30000
            }else if(s<=25000){
              j=s*0.2;
              //30001-40000
            }else if(s<=35000){
                j=s*0.25;
                //40000-50000
            }else if(s<=45000){
                j=s*0.3;
                //
            }else if(s<=60000){
                j=s*0.35;
            }else {
                j=s* num2;
            }
            System.out.println("你应交的税为："+j);
        }else{
            System.out.println("不交税！");
        }
    }
}
