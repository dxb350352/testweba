package java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

//方法引用
public class MethonQuote {
    public static MethonQuote create(Supplier<MethonQuote> supplier) {
        return supplier.get();
    }

    public static void collide(MethonQuote methonQuote) {
        System.out.println("Collided " + methonQuote.toString());
    }

    public void follow(final MethonQuote another) {
        System.out.println("Following the " + another.toString());
    }

    public void repair() {
        System.out.println("Repaired " + this.toString());
    }

    public static void main(String[] args) {
        //构造器引用，它的语法是Class::new，或者更一般的Class< T >::new。请注意构造器没有参数。
        MethonQuote methonQuote = MethonQuote.create(MethonQuote::new);
        List<MethonQuote> list = Arrays.asList(methonQuote);
        //静态方法引用，它的语法是Class::static_method。请注意这个方法接受一个MethonQuote类型的参数。
        list.forEach(MethonQuote::collide);
        //特定类的任意对象的方法引用，它的语法是Class::method。请注意，这个方法没有参数。
        list.forEach(MethonQuote::repair);
        //特定对象的方法引用，它的语法是instance::method。请注意，这个方法接受一个MethonQuote类型的参数
        list.forEach(methonQuote::follow);
    }
}
