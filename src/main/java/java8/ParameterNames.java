package java8;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class ParameterNames {
    public static void main(String[] args) throws Exception {
        Method method = ParameterNames.class.getMethod("main", String[].class);
        for (final Parameter parameter : method.getParameters()) {
            //如果不使用–parameters参数来编译这个类，然后运行这个类，会得到下面的输出：
            //如果使用–parameters参数来编译这个类，程序的结构会有所不同（参数的真实名字将会显示出来）
            System.out.println("Parameter: " + parameter.getName());
        }
    }
}
