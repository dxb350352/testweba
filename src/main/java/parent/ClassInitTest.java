package parent;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试Java类的成员和初始化块（代码块）初始化顺序
 * <p>
 * Created by lxk on 2017/4/20
 */
public class ClassInitTest<A,B,C,D> {
    public A test(B b,C c,D d){
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        return (A) new Object();
    }
    public static void main(String[] args) {
        System.out.println("测试代码开始");
        new Child();
        System.out.println("测试代码结束");
        ClassInitTest<Integer,Float,String,Double> test=new ClassInitTest();
        System.out.println(test.test(1.1f, "string", 1.2d));
    }
}