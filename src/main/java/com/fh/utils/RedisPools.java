package com.fh.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/*
    redis的连接池
 */
public class RedisPools {
    private static   JedisPool jedisPool;

    private RedisPools(){

    }
    //静态块的代码只能执行一次
    static {

        //创建连接池之前  设置连接池信息
        JedisPoolConfig poolConfig=new JedisPoolConfig();
        poolConfig.setMaxTotal(100);//最大连接数
        poolConfig.setMaxIdle(10);//最大空闲连接数
        poolConfig.setMinIdle(5);//最小空闲连接数
        //创建连接池对象
        jedisPool=new JedisPool(poolConfig,"192.168.52.131",6379);
    }

    public static Jedis getJedis(){
        Jedis resource = jedisPool.getResource();//从连接池中拿出一个
        resource.auth("zx");
        return resource;
    }

    public static void returnjedis(Jedis jedis){//把jedis还回连接池中
        if(jedis!=null){
            jedis.close();
        }
    }
}
