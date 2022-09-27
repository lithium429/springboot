package com.li.frame.spring;

import com.li.frame.spring.model.User;
import io.netty.util.HashedWheelTimer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CommonTest {

    @Test
    public void htw() throws IOException {
        HashedWheelTimer hwt = new HashedWheelTimer();
        hwt.newTimeout((timeout) -> {
            System.out.println("t5-2");
        }, 5, TimeUnit.SECONDS);
        hwt.newTimeout((timeout) -> {
            System.out.println("t1");
        }, 5, TimeUnit.SECONDS);
        hwt.newTimeout((timeout) -> System.out.println("t8"), 8, TimeUnit.SECONDS);
        System.in.read();
    }

    @Test
    public void tw1() throws IOException {
//        Timer timer=new Timer(1000,32);
//        timer.addTask(new TimerTask(200,()->{
//            System.out.println("delay 200ms running");
//        }));
//        timer.addTask(new TimerTask(1500,()->{
//            System.out.println("delay 1500ms running");
//        }));
//        System.in.read();
        int ticksPerWheel = 512;
        int normalizedTicksPerWheel = 1;
        while (normalizedTicksPerWheel < ticksPerWheel) {
            normalizedTicksPerWheel <<= 1;
        }
        System.out.println(normalizedTicksPerWheel);
    }


    @Test
    public void tw2() throws IOException {

        CompletableFuture<User> completableFuture = CompletableFuture.supplyAsync(() -> {
            User user = new User();
            user.setName("s");
            return user;
        }).thenApplyAsync(obj -> {
            obj.setAge(12);
            return obj;
        }).whenCompleteAsync((obj, e) -> {
            if (e != null) {
                log.error(e.getMessage(), e);
            }
        });

    }


}
