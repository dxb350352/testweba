package spring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Configuration
//开启spring对aspectJ的支持
@EnableAspectJAutoProxy
public class AOPOrder {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AOPOrder.class)) {
            AOPOrder order = (AOPOrder) context.getBean("AOPOrder");
            order.doWork();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AOPOrder.AOPOrderAnnotation
    public void doWork() {
        System.out.println("doWork");
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface AOPOrderAnnotation {
    }

    /**
     * Aspect、EnableAspectJAutoProxy不能同时在一个类上
     */
    @Component
    @Aspect
    class AOPOrderAspect {
        @Pointcut("@annotation(spring.AOPOrder.AOPOrderAnnotation)")
        public void pointcut() {
        }

        @Before("pointcut()")
        public void before() {
            System.out.println("before");
        }

        @After("pointcut()")
        public void after() {
            System.out.println("after");
        }

        @Around("pointcut()")
        public void around(ProceedingJoinPoint joinPoint) throws Throwable {
            System.out.println("around before");
            joinPoint.proceed();
            System.out.println("around after");
        }
    }


}
