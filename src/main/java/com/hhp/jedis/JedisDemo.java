package com.hhp.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;
import java.util.List;

public class JedisDemo {

    public static void main(String[] args) {
        // Create Jedis Pool Config
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(10);
        jedisPoolConfig.setMaxWait(Duration.ofMillis(2000));

        // Create Redis Connection Pool
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379);

        // Connect Redis via Jedis client, using try-with-resource
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.set("foo", "bar");
            String foobar = jedis.get("foo");
            System.out.println(foobar);
            jedis.zadd("sose", 0, "car");
            jedis.zadd("sose", 1, "bike");
            List<String> sose = jedis.zrange("sose", 0, -1);
            System.out.println(sose.toString());
        }
        jedisPool.close();
    }
}
