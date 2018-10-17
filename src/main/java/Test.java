import java.util.*;

public class Test {

    public static void main(String[] args) {
        int sshift = 0;
        int ssize = 1;
        while (ssize < 16) {
            ++sshift;
            ssize <<= 1;
        }
        int segmentShift = 32 - sshift;       // 偏移量值
        int segmentMask = ssize - 1;           // 掩码值
        System.out.println(("12321".hashCode() >>> segmentShift) & segmentMask);
        System.out.println("12321".hashCode());
        StringBuffer sb=new StringBuffer();
        sb.append(1);
        var set=new Vector<>();
    }
}
