package com.dxb;

import com.netflix.hystrix.*;

public class TimeOutCommand extends HystrixCommand<String> {
    private final String name;

    public TimeOutCommand(String name) {
        super(Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("LblCommandKey"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("LblThreadPoolKey"))
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter()
//                                .withExecutionTimeoutEnabled(false)//是否启用超时中断
                                .withExecutionTimeoutInMilliseconds(100)//配置超时时间
                                .withExecutionIsolationThreadInterruptOnTimeout(true)));//超时后是否中断
        this.name = name;
    }

    /**
     * 程序执行异常时可以通过getFallback进行降级操作
     *
     * @return
     */
    @Override
    protected String getFallback() {
        return "exeucute Falled!";
    }

    @Override
    protected String run() throws Exception {
        try {
            Thread.sleep(200);
            System.out.println("我没有被中断哦…………");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello " + name + " thread:" + Thread.currentThread().getName();
    }

    public static void main(String[] args) throws Exception {
        System.out.println(new TimeOutCommand("test-Fallback").execute());

        System.in.read();


    }
}