package com.dxb;

import com.netflix.hystrix.*;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class CircuitBreakerTest extends HystrixCommand<String> {

    private final String name;

    public CircuitBreakerTest(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("CircuitBreakerTestGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("CircuitBreakerTestKey"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("CircuitBreakerTest"))
                .andThreadPoolPropertiesDefaults(    // 配置线程池
                        HystrixThreadPoolProperties.Setter()
                                .withCoreSize(200)    // 配置线程池里的线程数，设置足够多线程，以防未熔断却打满threadpool
                )
                .andCommandPropertiesDefaults(    // 配置熔断器
                        HystrixCommandProperties.Setter()
                                .withCircuitBreakerEnabled(true) //默认true
                                .withCircuitBreakerErrorThresholdPercentage(20)     //（出错百分比阈值，当达到此阈值后，开始短路。默认50%）
                                .withCircuitBreakerRequestVolumeThreshold(3)        //// 在统计数据之前，必须在10秒内发出3个请求。  默认是20
                                .withCircuitBreakerSleepWindowInMilliseconds(8000)  //（断路多久以后开始尝试是否恢复，默认5s）
                )
        );
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        if (Integer.parseInt(name) == 1 || Integer.parseInt(name) == 17) {
            System.out.println(1 / 0);
        }
        return name;
    }

    @Override
    protected String getFallback() {
        return "CircuitBreaker fallback: " + name;
    }

    public static class UnitTest {
        @Test
        public void testxianliu() throws IOException, InterruptedException {
            for (int i = 0; i < 20; i++) {
                CircuitBreakerTest circuitBreakerTest = new CircuitBreakerTest(String.valueOf(i));
                String execute = circuitBreakerTest.execute();
                boolean responseFromFallback = circuitBreakerTest.isResponseFromFallback();
                System.out.println(execute + "; responseFromFallback: " + responseFromFallback + ".isCircuitBreakerOpen:" + circuitBreakerTest.isCircuitBreakerOpen());
                TimeUnit.SECONDS.sleep(1);
            }
        }
    }

}