package gc;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class Reference {
    public static void main(String[] args) {
        softReference();
        System.out.println("...................");
        weakReference();
        System.out.println("...................");
        phantomReference();

    }

    /**
     * 软引用:用于描述还有用但非必须的对象，如果内存不足，就回收
     */
    public static void softReference() {
        int[] ints = new int[300 * 1024 * 1024];
        SoftReference softReference = new SoftReference(ints);
        ints = null;
        System.out.println(softReference.get());
        ints = new int[300 * 1024 * 1024];
        System.out.println(softReference.get());
    }

    /**
     * 弱引用:遇到就回收
     */
    public static void weakReference() {
        String s = new String("123");

        ReferenceQueue<Object> objectReferenceQueue = new ReferenceQueue<>();
        WeakReference weakReference = new WeakReference<>(s, objectReferenceQueue);
        s = null;
        System.out.println(weakReference.get());
        System.gc();
        System.out.println(weakReference.get());
    }

    /**
     * 虚引用:如果一个对象仅持有虚引用，那么它就和没有任何引用一样，在任何时候都可能被垃圾回收器回收。 虚引用主要用来跟踪对象被垃圾回收器回收的活动。（遇到就回收，必须与ReferenceQueue一起使用）
     */
    public static void phantomReference() {
        String s = new String("123");
        ReferenceQueue<String> objectReferenceQueue = new ReferenceQueue<>();
        PhantomReference phantomReference = new PhantomReference<>(s, objectReferenceQueue);
        System.out.println(phantomReference.get());
        s = null;
        System.gc();
        System.out.println(objectReferenceQueue.poll() == phantomReference);
        System.out.println(phantomReference.get());
    }
}
