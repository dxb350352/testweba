package gc;

//https://github.com/Snailclimb/JavaGuide/blob/master/Java/%E6%90%9E%E5%AE%9AJVM%E5%9E%83%E5%9C%BE%E5%9B%9E%E6%94%B6%E5%B0%B1%E6%98%AF%E8%BF%99%E4%B9%88%E7%AE%80%E5%8D%95.md
//-XX:+PrintGCDetails
public class GCTest {
    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4, allocation5;
        allocation1 = new byte[60000 * 1024];
//        allocation2 = new byte[1000 * 1024];
//        allocation3 = new byte[1000 * 1024];
//        allocation4 = new byte[1000 * 1024];
//        allocation5 = new byte[1000 * 1024];
    }
}
