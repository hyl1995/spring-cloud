package org.hyl.cloud.eurekaclient.controller;

import org.hyl.cloud.common.DistributedLockHandler;
import org.hyl.cloud.common.Lock;
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
}
