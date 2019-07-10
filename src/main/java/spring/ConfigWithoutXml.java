package spring;

import org.springframework.context.annotation.*;

import java.util.Map;

@Configuration
@Import(TestBean.class)
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
        Map<String, TestBean> map = ctx.getBeansOfType(TestBean.class);
        map.forEach((k, v) -> {
            System.out.println(k + ":" + v);
        });

        //这里会销毁非prototype的bean
        ctx.close();

    }
}
