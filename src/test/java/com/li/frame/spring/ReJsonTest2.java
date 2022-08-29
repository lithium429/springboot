package com.li.frame.spring;

import com.li.frame.spring.runner.RedisConfig;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class ReJsonTest2 {

    JedisPool jedisPool;


    @Before
    public void before() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxIdle(2);
        jedisPool = new JedisPool(config, RedisConfig.SERVER, 6379, 3000, null, "123456", 1);
//        jedisPool = new JedisPool(RedisConfig.LOCAL, 6379);


    }

    @Test
    public void del() {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.set("tes1", "1");
            jedis.del("tes1");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            jedis.set("billing:1233421", "1");
            jedis.del("billing:1233421");
        }
    }
}
