package spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ConfigWithoutXml {

    public ConfigWithoutXml() {
        System.out.println("容器启动初始化。。。");
    }

    //@Bean注解注册bean,同时可以指定初始化和销毁方法
    @Bean(name = "testBean", initMethod = "start", destroyMethod = "cleanUp")
    @Scope("prototype")
    public TestBean testBean() {
        return new TestBean();
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigWithoutXml.class);
//        @Scope("prototype")
        TestBean testBean = (TestBean) ctx.getBean("testBean");
        testBean.sayHello();
        System.out.println(testBean);
        TestBean testBean2 = (TestBean) ctx.getBean("testBean");
        testBean2.sayHello();
        System.out.println(testBean2);
        //这里会销毁非prototype的bean
        ctx.close();

    }
}
