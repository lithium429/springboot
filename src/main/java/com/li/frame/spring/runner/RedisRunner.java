package com.li.frame.spring.runner;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class RedisRunner implements ApplicationRunner {


    @Override
    public void run(ApplicationArguments args) throws Exception {


        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxIdle(2);
//        JedisPool jedisPool = new JedisPool(config, RedisConfig.SERVER, 6379, 30, null, "123456",1);
        JedisPool jedisPool =new JedisPool(RedisConfig.LOCAL,6379);
        Jedis jedis = jedisPool.getResource();

        jedis.psubscribe(new RedisKeyListener(),"__keyevent@*__:del");

    }
}
