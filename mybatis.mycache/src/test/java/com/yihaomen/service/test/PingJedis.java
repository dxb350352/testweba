package com.yihaomen.service.test;

import redis.clients.jedis.Jedis;

/**
 *   
 *  @ProjectName: springmvc-mybatis 
 *  @Description:
 *  @author: lisen
 *  @date: 2017/11/9  
 */
public class PingJedis {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.130.230", 6379);
        System.out.println(jedis.ping());
    }
}
