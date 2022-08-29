package com.li.frame.spring.timewheel;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

public class TimerTaskList implements Delayed {

    /**
     * 过期时间
     */
    private AtomicLong expiration = new AtomicLong(-1L);

    /**
     * 跟节点
     */
    private TimerTask root = new TimerTask(-1, null);

    {
        root.prev = root;
        root.next = root;
    }

    public boolean setExpiration(long expire) {
        return expiration.getAndSet(expire) != expire;
    }

    public long getExpiration() {
        return expiration.get();
    }

    /**
     * 新增任务
     */
    public void addTask(TimerTask task) {
        synchronized (this) {
            if (task.timerTaskList == null) {
                task.timerTaskList = this;
                TimerTask tail = root.prev;
                task.next = root;
                task.prev = tail;
                tail.next = task;
                root.prev = task;
            }
        }
    }

    /**
     * 移除任务
     */
    public void removeTask(TimerTask task) {
        synchronized (this) {
            if (task.timerTaskList.equals(this)) {
                task.next.prev = task.prev;
                task.prev.next = task.next;
                task.timerTaskList = null;
                task.next = null;
                task.prev = null;
            }
        }
    }

    /**
     * 重新分配
     */
    public synchronized void flush(Consumer<TimerTask> flush) {
        TimerTask timerTask = root.next;
        while (!timerTask.equals(root)) {
            this.removeTask(timerTask);
            flush.accept(timerTask);
            timerTask = root.next;
        }
        expiration.set(-1L);
    }


    @Override
    public long getDelay(TimeUnit unit) {
        return Math.max(0, unit.convert(expiration.get() - System.currentTimeMillis(), TimeUnit.MILLISECONDS));
    }

    @Override
    public int compareTo(Delayed o) {
        if (o instanceof TimerTaskList) {
            return Long.compare(expiration.get(), ((TimerTaskList) o).expiration.get());
        }
        return 0;
    }
}
