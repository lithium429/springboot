package com.li.frame.spring.timewheel;

import java.util.concurrent.DelayQueue;

public class TimeWheel {

    /**
     * 一个时间槽范围
     */
    private long tick;

    /**
     * 时间轮大小
     */
    private int wheelSize;

    /**
     * 时间跨度
     */
    private long interval;

    /**
     * 时间槽
     */
    private TimerTaskList[] timerTaskLists;

    /**
     * 当前时间
     */
    private long currentTime;

    /**
     * 上层时间轮
     */
    private volatile TimeWheel overTimeWheel;

    private DelayQueue<TimerTaskList> delayQueue;

    public TimeWheel(long tick, int wheelSize, DelayQueue<TimerTaskList> delayQueue, long currentTime) {
        this.tick = tick;
        this.wheelSize = wheelSize;
        this.interval = tick * wheelSize;
        this.timerTaskLists = new TimerTaskList[wheelSize];
        this.currentTime = currentTime - (currentTime % tick);
        this.delayQueue = delayQueue;
        for (int i = 0; i < wheelSize; i++) {
            timerTaskLists[i] = new TimerTaskList();
        }
    }

    private TimeWheel getOverTimeWheel() {
        if (overTimeWheel == null) {
            synchronized (this) {
                if (overTimeWheel == null) {
                    overTimeWheel = new TimeWheel(interval, wheelSize, delayQueue, currentTime);
                }
            }
        }
        return overTimeWheel;
    }

    public boolean addTask(TimerTask task) {
        long expiration = task.getDelayMs();
        if (expiration < currentTime + tick) {
            return false;
        } else if (expiration < currentTime + interval) {
            long virtualId = expiration / tick;
            int index = (int) (virtualId % wheelSize);
            TimerTaskList timerTaskList = timerTaskLists[index];
            timerTaskList.addTask(task);
            if (timerTaskList.setExpiration(virtualId * tick)) {
                delayQueue.offer(timerTaskList);
            }
        } else {
            TimeWheel timeWheel = getOverTimeWheel();
            timeWheel.addTask(task);
        }
        return true;
    }

    public void advanceClock(long timestamp) {
        if (timestamp > currentTime + tick) {
            currentTime = timestamp - (timestamp % tick);
            if (overTimeWheel != null) {
                getOverTimeWheel().advanceClock(timestamp);
            }
        }

    }
}
