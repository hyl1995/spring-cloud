package org.hyl.cloud.index.controller;

import org.hyl.cloud.common.util.DistributedLockHandler;
import org.hyl.cloud.common.util.Lock;
import org.hyl.cloud.common.util.redisson.AquiredLockWorker;
import org.hyl.cloud.common.util.redisson.DistributedLocker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class HelloController {

    @Value("${server.port}")
    private int port;
    @Autowired
    private DistributedLockHandler distributedLockHandler;
    @Autowired
    private DistributedLocker distributedLocker;


    @RequestMapping("index")
    public String index(){
        return "hby专属接口!,端口："+port;
    }

    @RequestMapping("lock")
    public String lock(){
        Lock lock=new Lock("hyl","hby");
        if(distributedLockHandler.tryLock(lock)){
            try {
                //为了演示锁的效果，这里睡眠5000毫秒
                System.out.println("执行方法");
                Thread.sleep(5000);
            }catch (Exception e){
                e.printStackTrace();
            }
            distributedLockHandler.releaseLock(lock);
        }
        return "hello world!";
    }

    @RequestMapping("redlock")
    public String redlock()throws Exception{
        distributedLocker.lock("test",new AquiredLockWorker<Object>() {

            @Override
            public Object invokeAfterLockAquire() {
                try {
                    System.out.println("执行方法！");
                    Thread.sleep(5000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                return null;
            }

        });
        return "hello world!";
    }

    @RequestMapping("zookeeperlock")
    public String zookeeperlock()throws Exception{
//        DistributedLock lock   = new DistributedLock("localhost:2181","lock");
//        lock.lock();
//        //共享资源
//        if(lock != null){
//            System.out.println("执行方法");
//            Thread.sleep(5000);
//            lock.unlock();
//        }
        return "hello world!";
    }
}
