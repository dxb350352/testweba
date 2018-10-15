package proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class ProxyCglib implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib proxy start");
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("cglib proxy end");
        return result;
    }

    public static void main(String[] arsg) {
        ProxyCglib proxyCglib = new ProxyCglib();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(IUserImp.class);
        enhancer.setCallback(proxyCglib);
        IUser IUserProxy = (IUser) enhancer.create();
        IUserProxy.getName();
        IUserProxy.getAge();
    }
}
