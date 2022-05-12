package com.hhp.jedis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

public class JedisClusterDemo {

    public static void main(String[] args) {
        // Define a redis cluster host & port list
        Set<HostAndPort> jedisClusterHostAndPorts = new HashSet<>();
        jedisClusterHostAndPorts.add(new HostAndPort("127.0.0.1", 6379));
        jedisClusterHostAndPorts.add(new HostAndPort("127.0.0.1", 6380));
        jedisClusterHostAndPorts.add(new HostAndPort("127.0.0.1", 6381));
        jedisClusterHostAndPorts.add(new HostAndPort("127.0.0.1", 6382));
        JedisCluster jedis = new JedisCluster(jedisClusterHostAndPorts);

        // Now you can use JedisCluster to access Redis read / write
        jedis.sadd("key", "values");
    }
}
