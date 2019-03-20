package com.dxb.springBootDemo;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/hystrix")
public class HystrixController {

    private static final Logger LOG = LoggerFactory.getLogger(HystrixController.class);

    /**
     * groupKey
     * 表示所属的group，一个group共用线程池
     * 默认值：getClass().getSimpleName();
     *
     * commandKey
     *  	默认值：当前执行方法名
     *
     * execution.isolation.strategy
     *
     * 隔离策略，有THREAD和SEMAPHORE
     * 默认使用THREAD模式，以下几种可以使用SEMAPHORE模式：
     *
     * 只想控制并发度
     * 外部的方法已经做了线程隔离
     * 调用的是本地方法或者可靠度非常高、耗时特别小的方法（如medis）
     *
     * execution.isolation.thread.timeoutInMilliseconds
     * 超时时间
     * 默认值：1000
     *
     * 在THREAD模式下，达到超时时间，可以中断
     *
     * 在SEMAPHORE模式下，会等待执行完成后，再去判断是否超时
     *
     * 设置标准：
     *
     * 有retry，99meantime+avg meantime
     *
     * 没有retry，99.5meantime
     *
     * execution.timeout.enabled
     *
     * 是否打开超时
     * execution.isolation.thread.interruptOnTimeout
     *
     * 是否打开超时线程中断	THREAD模式有效
     * execution.isolation.semaphore.maxConcurrentRequests
     *
     * 信号量最大并发度	SEMAPHORE模式有效，默认值：10
     * fallback.isolation.semaphore.maxConcurrentRequests
     *
     * fallback最大并发度	默认值：10
     * circuitBreaker.requestVolumeThreshold
     *
     * 熔断触发的最小个数/10s	默认值：20
     * circuitBreaker.sleepWindowInMilliseconds
     *
     * 熔断多少秒后去尝试请求	默认值：5000
     * circuitBreaker.errorThresholdPercentage
     *
     * 失败率达到多少百分比后熔断
     * 默认值：50
     *
     * 主要根据依赖重要性进行调整
     *
     * circuitBreaker.forceClosed
     *
     * 是否强制关闭熔断	如果是强依赖，应该设置为true
     * coreSize
     *
     * 线程池coreSize
     * 默认值：10
     *
     * 设置标准：qps*99meantime+breathing room
     *
     * maxQueueSize
     *
     * 请求等待队列
     * 默认值：-1
     *
     * 如果使用正数，队列将从SynchronizeQueue改为LinkedBlockingQueue
     */
    @RequestMapping(value = "/ok", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "okFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "200"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "50")

    })
    public String ok() throws Exception {
        int l = new Random().nextInt(300);
        LOG.info(String.format("l=%s", l));
        if (l < 100) {
            System.out.println(l);
            throw new Exception("xxx");
        }
        TimeUnit.MILLISECONDS.sleep(l);
        return "ok";
    }

    public String okFallback(Throwable e) {
        System.out.println("execute okFallback!" + e.getMessage());
        LOG.error("error", e);
        return "fallbackerror";
    }

    public String okFallback() {
        return "fallbackssssss";
    }

}