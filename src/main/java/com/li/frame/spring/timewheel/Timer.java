package com.li.frame.spring.timewheel;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Timer {

    private DelayQueue<TimerTaskList> delayQueue;

    /**
     * 底层时间轮
     */
    private TimeWheel timeWheel;

    /**
     * 过期任务执行线程
     */
    private ExecutorService workThreadPool;

    /**
     * 轮询delayQueue获取过期任务线程
     */
    private ExecutorService bossThreadPool;

    public Timer(long tick, int wheelSize) {
        this.delayQueue = new DelayQueue<>();
        this.timeWheel = new TimeWheel(tick, wheelSize, delayQueue, System.currentTimeMillis());
        this.workThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2 + 2);
        this.bossThreadPool = Executors.newFixedThreadPool(1);
        this.bossThreadPool.submit(() -> {
            while (true) {
                this.advanceClock(tick * wheelSize);
            }
        });
    }

    public void addTask(TimerTask task) {
        if (!timeWheel.addTask(task)) {
            workThreadPool.submit(task.getTask());
        }
    }

    private void advanceClock(long timeout) {
        try {
            TimerTaskList timerTaskList = delayQueue.poll(timeout, TimeUnit.MILLISECONDS);
            if (timerTaskList != null) {
                timeWheel.advanceClock(timerTaskList.getExpiration());
                timerTaskList.flush(this::addTask);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
