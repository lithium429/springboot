package com.li.frame.spring;

import com.li.frame.spring.timewheel.Timer;
import com.li.frame.spring.timewheel.TimerTask;
import io.netty.util.HashedWheelTimer;
import org.junit.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

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
        int ticksPerWheel=512;
        int normalizedTicksPerWheel = 1;
        while (normalizedTicksPerWheel < ticksPerWheel) {
            normalizedTicksPerWheel <<= 1;
        }
        System.out.println(normalizedTicksPerWheel);
    }
}
