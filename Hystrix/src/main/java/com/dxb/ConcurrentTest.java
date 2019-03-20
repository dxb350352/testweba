package com.dxb;

import com.netflix.hystrix.*;
import com.netflix.hystrix.HystrixCommandProperties.ExecutionIsolationStrategy;
import org.junit.Test;

import java.io.IOException;

/**
 * 并发测试
 * 默认执行run()用的是主线程，为了模拟并行执行command，这里我们自己创建多个线程来执行command
 * 设置ExecutionIsolationSemaphoreMaxConcurrentRequests为3，意味着信号量最多允许执行run的并发数为3，超过则触发降级，即不执行run而执行getFallback
 * 设置FallbackIsolationSemaphoreMaxConcurrentRequests为2，意味着信号量最多允许执行fallback的并发数为2，超过则抛异常fallback execution rejected
 */
public class ConcurrentTest extends HystrixCommand<String> {

    private final String name;

    public ConcurrentTest(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("SemaphoreTestGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("SemaphoreTestKey"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("SemaphoreTestThreadPoolKey"))
                .andCommandPropertiesDefaults(    // 配置信号量隔离
                        HystrixCommandProperties.Setter()
                                .withExecutionIsolationStrategy(ExecutionIsolationStrategy.SEMAPHORE)    // 信号量隔离
                                .withExecutionIsolationSemaphoreMaxConcurrentRequests(3)
                                .withFallbackIsolationSemaphoreMaxConcurrentRequests(2)
                )
        );
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        Thread.sleep(100);//模拟执行时间
        return "run(): name=" + name + "，线程名是" + Thread.currentThread().getName();
    }

    @Override
    protected String getFallback() {
        try {
            Thread.sleep(100);//模拟执行时间
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "getFallback(): name=" + name + "，线程名是" + Thread.currentThread().getName();
    }

    public static class UnitTest {

        @Test
        public void testSynchronous() throws IOException {

            try {
                for (int i = 0; i < 10; i++) {
                    final int j = i;
                    // 自主创建线程来执行command，创造并发场景
                    Thread thread = new Thread(() ->
                            System.out.println("===========" + new ConcurrentTest("HLX" + j).execute())    // 被信号量拒绝的线程从这里抛出异常
                    );
                    thread.start();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.in.read();
        }
    }

}