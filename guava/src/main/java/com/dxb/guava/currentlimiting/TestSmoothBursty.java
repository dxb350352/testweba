package com.dxb.guava.currentlimiting;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

public class TestSmoothBursty {
    public static void main(String[] args) throws Exception {
        test01();
    }

    public static void test01() throws Exception {
        RateLimiter rateLimiter = RateLimiter.create(2.0);  //创建一个QPS=2的限流器
        System.out.println(rateLimiter.acquire(1));  //不会等待
        System.out.println(rateLimiter.acquire(1));  //等待0.5秒，债务转移
        TimeUnit.SECONDS.sleep(2);
        //因为闲置了一段时间，桶中已经存在令牌，能够应对突发流量
        System.out.println(rateLimiter.acquire(1));  //不会等待
        System.out.println(rateLimiter.acquire(1));  //不会等待
        System.out.println(rateLimiter.acquire(1));  //不会等待
        System.out.println(rateLimiter.acquire(1));  //等待0.5秒，债务转移
        System.out.println(rateLimiter.acquire(1));  //等待0.5秒，债务转移
        System.out.println(rateLimiter.acquire(1));  //等待0.5秒，债务转移
        System.out.println(rateLimiter.acquire(1));  //等待0.5秒，债务转移
        System.out.println(rateLimiter.acquire(1));  //等待0.5秒，债务转移
        //流量饱和，请求会以2个/秒的速度执行
    }
}
