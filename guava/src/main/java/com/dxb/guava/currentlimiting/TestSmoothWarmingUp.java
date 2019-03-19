package com.dxb.guava.currentlimiting;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

public class TestSmoothWarmingUp {
    public static void main(String[] args) throws InterruptedException {
        RateLimiter limiter = RateLimiter.create(2, 3000, TimeUnit.MILLISECONDS);
        for (int i = 0; i < 10; i++) {
            System.out.println(limiter.acquire());
        }
        System.out.println("sleep");
        Thread.sleep(1000L);
        for (int i = 0; i < 10; i++) {
            System.out.println(limiter.acquire());
        }
    }
}
