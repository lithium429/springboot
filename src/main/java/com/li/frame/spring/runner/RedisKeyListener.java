package com.li.frame.spring.runner;

import redis.clients.jedis.JedisPubSub;

public class RedisKeyListener extends JedisPubSub {

    @Override
    public void onMessage(String channel, String message) {
        System.out.println(channel + " | " + message);
        super.onMessage(channel, message);
    }


    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
        System.out.println(pattern);
        System.out.println(subscribedChannels);
    }

    @Override
    public void onPMessage(String pattern, String channel, String message) {
        System.out.println(pattern);
        System.out.println(channel);
        System.out.println(message);
        super.onPMessage(pattern, channel, message);
    }
}
