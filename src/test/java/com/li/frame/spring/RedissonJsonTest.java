package com.li.frame.spring;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Before;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RJsonBucket;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JacksonCodec;
import org.redisson.codec.JsonCodec;
import org.redisson.config.Config;

import java.util.HashMap;

public class RedissonJsonTest {

    Config config;
    RedissonClient redissonClient;

    @Before
    public void before() {
        config = new Config();
        config.useSingleServer().setAddress("redis://192.168.6.49:6379").setDatabase(1).setPassword("123456");
        redissonClient = Redisson.create(config);
    }

    @Test
    public void json1(){
        RJsonBucket<User> bucket = redissonClient.getJsonBucket("user:1234", new JacksonCodec<>(User.class));
        bucket.set(new User("zhang",12));
        User user = bucket.get();
        System.out.println(user);
    }

    @Test
    public void json2(){
        RJsonBucket<User> bucket = redissonClient.getJsonBucket("user:5231", new JacksonCodec<>(User.class));
//        User user=new User("zhs",81);
//        bucket.set(user);
//        user.setName("dhhwn");
//        bucket.set("address","都");
        //bucket.delete("name");
        String name = bucket.getName();
        System.out.println(name);
    }
    @Data
    @AllArgsConstructor
    public class User{
        private String name;
        private Integer age;
    }

}
