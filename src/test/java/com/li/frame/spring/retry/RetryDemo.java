package com.li.frame.spring.retry;

import com.github.rholder.retry.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.remoting.RemoteAccessException;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
public class RetryDemo {

    public static boolean retryTask() {

        int i = randInt(1, 11);

        log.info("随机生成的数:{}", i);
        if (i < 2) {
            log.info("为0,抛出参数异常.");
            throw new IllegalArgumentException("参数异常");
        } else if (i < 5) {
            log.info("为1,返回true.");
            return true;
        } else if (i < 7) {
            log.info("为2,返回false.");
            return false;
        } else {
            //为其他
            log.info("大于2,抛出自定义异常.");
            throw new RemoteAccessException("大于2,抛出自定义异常");
        }
    }

    public static int randInt(int min, int max) {
        Random random = new Random(System.currentTimeMillis());
        return random.nextInt(max - min + 1) + min;
    }


    @Test
    public void retry1() {
        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder().retryIfExceptionOfType(RemoteAccessException.class)
                .retryIfResult(res -> res == false)
                .withWaitStrategy(WaitStrategies.fixedWait(3, TimeUnit.SECONDS))
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                .build();

        try {
            retryer.call(() -> RetryDemo.retryTask());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
