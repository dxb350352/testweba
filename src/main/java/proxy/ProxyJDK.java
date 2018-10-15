package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyJDK implements InvocationHandler {
    private Object target;

    public ProxyJDK(Object target) {
        super();
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("jdk proxy start");
        Object result = method.invoke(this.target, args);
        System.out.println("jdk proxy end");
        return result;
    }

    public static void main(String[] args) {
        IUser IUser = new IUserImp();
        InvocationHandler proxy = new ProxyJDK(IUser);
        IUser IUserProxy = (IUser) Proxy.newProxyInstance(IUser.getClass().getClassLoader(), IUser.getClass().getInterfaces(), proxy);
        IUserProxy.getAge();
        IUserProxy.getName();
    }
}
