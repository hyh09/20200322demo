package com.example.demo_sanm.thread;




/**
 * @单位名称：科大国创—电信资源事业部
 * @创建人：hu.yunhui
 * @创建时间: on 2020/4/17.
 * @by: DELL)
 */
public class TreadVolatie extends  Thread {

    public  volatile Boolean flg =true;

    @Override
    public void run() {
        System.out.println("开始启动子线程"+flg);


        while (flg){
           // System.out.println("##########");

        }
        if(!flg){
            System.out.println("##########");
        }

        System.out.println("#####子线程结束#####");


    }

    public Boolean getFlg() {
        return flg;
    }

    public void setFlg(Boolean flg) {
        this.flg = flg;
    }


    public static void main(String[] args) throws InterruptedException {
        TreadVolatie threadVolatileDemo = new TreadVolatie();
        threadVolatileDemo.start();
        Thread.sleep(3000);
        threadVolatileDemo.setFlg(false);
        System.out.println("flag 已经设置成false");

        Thread.sleep(1000);
        System.out.println(threadVolatileDemo.flg);

    }




}
