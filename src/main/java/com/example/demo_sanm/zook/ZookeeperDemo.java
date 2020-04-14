package com.example.demo_sanm.zook;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @单位名称：科大国创—电信资源事业部
 * @创建人：hu.yunhui
 * @创建时间: on 2020/4/13.
 * @by: DELL)
 *
 *
 *
 * 使用Java语言操作zk
 */
public class ZookeeperDemo {
    /**
     * 集群连接地址
     */
    private static final String CONNECT_ADDR = "14.144.245.249:2181";//端口号可以在
    // "192.168.110.138:2181,192.168.110.147:2181,192.168.110.148:2181";


    /**
     * session超时时间
     */
    private static final int SESSION_OUTTIME = 2000;

    /**
     * 信号量,阻塞程序执行,用户等待zookeeper连接成功,发送成功信号，
     */
    private static final CountDownLatch countDownLatch = new CountDownLatch(1);



    @Test
    public  void Test2() throws IOException, InterruptedException, KeeperException {
        ZooKeeper  zooKeeper=   new ZooKeeper(CONNECT_ADDR, SESSION_OUTTIME, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                // 获取时间的状态
                Event.KeeperState keeperState = event.getState();
                Event.EventType tventType = event.getType();
                // 如果是建立连接
                if (Event.KeeperState.SyncConnected == keeperState) {
                    if (Event.EventType.None == tventType) {
                        // 如果建立连接成功,则发送信号量,让后阻塞程序向下执行
                        System.out.println("zk 建立连接");
                        /**建立连接 唤醒其他线程 */
                        countDownLatch.countDown();

                    }
                }

            }
        });
     //   Thread.sleep(10000);

        /**   让主线程等待5秒, 让主线程等待{ 类似多线程中的 join} */
        countDownLatch.await();
        System.out.println("开始创建");
                /**
                 * 参数一：路径地址
                 * 参数二：想要保存的数据，需要转换成字节数组
                 * 参数三：ACL访问控制列表（Access control list）,
                 *      参数类型为ArrayList<ACL>，Ids接口提供了一些默认的值可以调用。
                 *      OPEN_ACL_UNSAFE     This is a completely open ACL
                 *                          这是一个完全开放的ACL，不安全
                 *      CREATOR_ALL_ACL     This ACL gives the
                 *                           creators authentication id's all permissions.
                 *                          这个ACL赋予那些授权了的用户具备权限
                 *      READ_ACL_UNSAFE     This ACL gives the world the ability to read.
                 *                          这个ACL赋予用户读的权限，也就是获取数据之类的权限。
                 *
                 *
                 *
                 *
                 * 参数四：创建的节点类型。枚举值CreateMode
                 *      PERSISTENT (0, false, false)
                 *      PERSISTENT_SEQUENTIAL (2, false, true)
                 *          这两个类型创建的都是持久型类型节点，回话结束之后不会自动删除。
                 *          区别在于，第二个类型所创建的节点名后会有一个单调递增的数值
                 *      EPHEMERAL (1, true, false)
                 *      EPHEMERAL_SEQUENTIAL (3, true, true)
                 *          这两个类型所创建的是临时型类型节点，在回话结束之后，自动删除。
                 *          区别在于，第二个类型所创建的临时型节点名后面会有一个单调递增的数值。
                 * 最后create()方法的返回值是创建的节点的实际路径
                 */

//       if(!this.doSomething(zooKeeper)){
//           String result= zooKeeper.create("/node", "huyunhui".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
//           System.out.println("############创建节点成功##########"+result);
//       }else {
//
//              zooKeeper.delete("/node",-1);
//       }
        String result= zooKeeper.create("/node", "huyunhui".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
           System.out.println("############创建节点成功##########"+result);
           Thread.sleep(5000);
           for(int  i=0;i<12;i++){
               Thread.sleep(5000);
               System.out.println("############休息"+i+"下##########"+result);


           }
        countDownLatch.await();

//
//
//
//
//                System.out.println("#########开始获取节点的信息#########");
//        byte[]  bytes=   zooKeeper.getData("/node",false,null);
//        System.out.println("#############获取节点的信息数据########################"+new String(bytes));


        zooKeeper.close();//关闭连接

    }


    /**
     * 判断节点是否存在
     * @param zooKeeper
     */
    private Boolean doSomething(ZooKeeper zooKeeper){
        try{
            Stat stat = zooKeeper.exists("/node", true);
            if(stat == null){
                System.out.println("节点不存在");
                return  false;
            }else{
                System.out.println("doSomething-stat:" + stat);
                return  true;
            }
        }catch(Exception e){
            System.out.println("e"+e);
        }
        return  false;
    }








}
