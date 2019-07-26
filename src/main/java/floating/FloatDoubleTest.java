package floating;

/**
 * https://www.cnblogs.com/yewsky/articles/1864934.html
 */
public class FloatDoubleTest {
    public static void main(String[] args) {
        float f = 20014999;
        double d = f;
        double d2 = 20014999;
        System.out.println("f=" + f);
        System.out.println(Integer.toBinaryString(Float.floatToIntBits(f)));
        System.out.println("d=" + d);
        System.out.println("d2=" + d2);
        System.out.println(Long.toBinaryString(Double.doubleToLongBits(d2)));
    }
}