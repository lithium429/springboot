package com.li.frame.spring.timewheel;

public class TimerTask {

    /**
     * 延迟时间
     */
    private long delay;

    /**
     * 任务
     */
    private Runnable task;

    /**
     * 时间槽
     */
    protected TimerTaskList timerTaskList;

    /**
     * 下一个节点
     */
    protected TimerTask next;

    /**
     * 上一个节点
     */
    protected TimerTask prev;

    /**
     * 描述
     */
    public String desc;

    public TimerTask(long delay, Runnable task) {
        this.delay = System.currentTimeMillis() + delay;
        this.task = task;
        this.timerTaskList = null;
        this.next = null;
        this.prev = null;
    }

    public Runnable getTask() {
        return task;
    }

    public long getDelayMs() {
        return delay;
    }

    @Override
    public String toString() {
        return "TimerTask{" +
                "desc='" + desc + '\'' +
                '}';
    }
}
