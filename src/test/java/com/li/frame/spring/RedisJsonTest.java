package com.li.frame.spring;

import com.li.frame.spring.model.User;
import lombok.Data;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisClientConfig;
import redis.clients.jedis.UnifiedJedis;
import redis.clients.jedis.json.Path;
import redis.clients.jedis.json.Path2;
import redis.clients.jedis.providers.PooledConnectionProvider;


public class RedisJsonTest {
    private UnifiedJedis client;

    String address = "192.168.6.49";


    @Before
    public void before() {
        HostAndPort config = new HostAndPort(address, 6379);
        JedisClientConfig jedisClientCon = DefaultJedisClientConfig.builder().user(null).password("123456").database(1).build();

        PooledConnectionProvider provider = new PooledConnectionProvider(config, jedisClientCon);
        client = new UnifiedJedis(provider);
    }

    @Test
    public void jt1() {

        User user = new User();
        user.setId(1002L);
        user.setIp(2L);
        user.setName("lisit");


        Student maya = new Student("Maya", "Jayavant");

        client.jsonSet("student:111", Path.ROOT_PATH, maya);

        maya.setFirstName("maylol");
        client.jsonSetLegacy("student:111", maya);

        //client.jsonSet("user:121", Path.ROOT_PATH, data);

//        Map dev1 = client.jsonGet("dev1", Map.class);
        Student student = client.jsonGet("student:111", Student.class);
        System.out.println(student);


//        client.jsonSet("student:111", Path.ROOT_PATH,"")
    }

    private class Student {
        private String firstName;
        private String lastName;

        private Integer age;
        private Long bal;

        public Student(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }


        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Long getBal() {
            return bal;
        }

        public void setBal(Long bal) {
            this.bal = bal;
        }
    }

    @Data
    private class Device {
        private String id;
        private String deviceNo;
        private Double read;
    }

    @Test
    public void jt2() {
        String s = client.jsonSetWithEscape("deviceprocesscache:c4abb0f8f2234c868acdb9c56df82dcf", Path2.of("companyId"), 5445);
        System.out.println(s);

//        Object o = client.jsonGet("student:111", new Path2("user.ip"));
//        System.out.println(1);
    }

    @Test
    public void jt3() {
        Device dev = new Device();
        dev.setDeviceNo("WP2891");
        dev.setId("12PW");
        dev.setRead(1291.23D);

        this.set("device:" + dev.getId(), dev);
        System.out.println(1);
    }

    public <T> void set(String key, T t) {
        String s = client.jsonSetLegacy(key, t);
        System.out.println(s);
    }




    @Test
    public void jt4() {
        Device dev = new Device();
        dev.setDeviceNo("WP2891");
        dev.setId("12PW");
        dev.setRead(1291.23D);

        this.update("device:" + dev.getId(), "read", 981.82D);
        System.out.println(1);
    }

    public <T> void update(String key, String path, T value) {
        String s = client.jsonSet(key, new Path2(path), value);
        System.out.println(s);
    }

    @Test
    public void jt5() {
        Device dev = new Device();
        dev.setDeviceNo("WP2891");
        dev.setId("12PW");
        dev.setRead(1291.23D);

//        long read = this.delField("device:" + dev.getId(), "read");
        long read = this.delete("device:" + dev.getId());
        System.out.println(read);
    }

    public long delField(String key, String path) {
        return client.jsonDel(key, new Path2(path));
    }

    public long delete(String key) {
        return client.jsonDel(key);
    }

    @Test
    public void jt6() {
        Device dev = new Device();
        dev.setDeviceNo("WP2891");
        dev.setId("12PW");
        dev.setRead(1291.23D);

//        long read = this.delField("device:" + dev.getId(), "read");
        Device red = this.getObject("device:12PW", Device.class);
        System.out.println(red);
    }

    public <T> T getObject(String key, Class<T> clazz) {

        return client.jsonGet(key, clazz);
    }
}
