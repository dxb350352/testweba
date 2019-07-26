package floating;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class BigDecimalTest {
    public static void main(String[] args) {
        System.out.println("BigDecimal下有个坑,当传入浮点类型新建BigDecimal时,生成的BigDecimal对象会产生小数位偏差,当进行四舍五入的时候,建议BigDecimal不要传入浮点数类型,而是传入字符串类型");
        System.out.println("10.2335->" + new BigDecimal(10.2335));
        System.out.println("10.2345->" + new BigDecimal(10.2345));
        System.out.println("\"10.2345\"->" + new BigDecimal("10.2345"));
        //HALF_UP,经典的四舍五入
        String a = "10.2335";
        BigDecimal b0 = new BigDecimal(a);
        double f0 = b0.setScale(3, RoundingMode.HALF_UP).doubleValue();
        System.out.println("10.2335->HALF_UP->" + f0);
        // HALF_DOWN,当5为舍弃位,且为维一舍弃位时,5舍
        String b = "10.2335";
        BigDecimal b1 = new BigDecimal(b);
        double f1 = b1.setScale(3, RoundingMode.HALF_DOWN).doubleValue();
        System.out.println("10.2335->HALF_DOWN->" + f1);
        String c = "10.23352";
        BigDecimal b2 = new BigDecimal(c);
        double f2 = b2.setScale(3, RoundingMode.HALF_DOWN).doubleValue();
        System.out.println("10.23352->HALF_DOWN->" + f2);
//        HALF_EVEN,四舍六入(银行家算法)
//        舍去位的数值小于5时，直接舍去。
//        舍去位的数值大于5时，进位后舍去。
//        当舍去位的数值等于5时，若5后面还有其他非0数值，则进位后舍去，
//        若5后面是0时，则根据5前一位数的奇偶性来判断，奇数进位，偶数舍去。
        String d = "1.2345";
        BigDecimal b3 = new BigDecimal(d);
        double f3 = b3.setScale(3, RoundingMode.HALF_EVEN).doubleValue();
        System.out.println("1.2345->HALF_EVEN->" + f3);
        String e = "1.2355";
        BigDecimal b4 = new BigDecimal(e);
        double f4 = b4.setScale(3, RoundingMode.HALF_EVEN).doubleValue();
        System.out.println("1.2355->HALF_EVEN->" + f4);

        System.out.println("NumberFormat 只能用作科学计算或者是工程计算,而不能用作商业计算,会产生精度缺失");
//        向上取整
        NumberFormat nf1 = NumberFormat.getNumberInstance();
        nf1.setMaximumFractionDigits(2);
        nf1.setRoundingMode(RoundingMode.UP);
        System.out.println("2.574->UP->" + nf1.format(2.574));//输出:2.58
//        2.向下取整
        NumberFormat nf2 = NumberFormat.getNumberInstance();
        nf2.setMaximumFractionDigits(2);
        nf2.setRoundingMode(RoundingMode.DOWN);
        System.out.println("2.579->DOWN->" + nf2.format(2.579));//输出:2.57
//        3.最近数字舍入(五进)
        NumberFormat nf3 = NumberFormat.getNumberInstance();
        nf3.setMaximumFractionDigits(2);
        nf3.setRoundingMode(RoundingMode.HALF_UP);
        System.out.println("2.575->HALF_UP->" + nf3.format(2.575));   //输出:2.58
        System.out.println("2.565->HALF_UP->(精度缺失导致5没有进位)" + nf3.format(2.565));   //输出:2.56 (精度缺失导致5没有进位)
//        4.最近数字舍入(五舍)
        NumberFormat nf4 = NumberFormat.getNumberInstance();
        nf4.setMaximumFractionDigits(2);
        nf4.setRoundingMode(RoundingMode.HALF_DOWN);
        System.out.println("2.575->HALF_DOWN->(精度缺失导致5进位)" + nf4.format(2.575)); //输出:2.58 (精度缺失导致5进位)
        System.out.println("2.565->HALF_DOWN->" + nf4.format(2.565)); //输出:2.56

        System.out.println("DecimalFormat是NumberFormat的子类,同样会有精度缺失");
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println("2.565->" + df.format(2.565));//输出:2.56
        System.out.println("2.575->" + df.format(2.575));//输出:2.58
        System.out.println("真正的四舍五入(目前没发现精度缺失的情况)");
        System.out.println(String.format("%.2f", 2.565)); //输出:2.57
        System.out.println(String.format("%.2f", 2.575));//输出:2.58
        System.out.println(String.format("%.2f", 2.564));//输出:2.56
        System.out.println(String.format("%.2f", -2.565));//输出:-2.57
        System.out.println(String.format("%.2f", -2.575));//输出:-2.58
        System.out.println(String.format("%.2f", -2.564));//输出:-2.56
        System.out.println("Math.round()");
        System.out.println("1.参数的小数点后第一位<5，运算结果为参数整数部分。");
        System.out.println("2.参数的小数点后第一位>5，运算结果为参数整数部分绝对值+1，符号（即正负）不变。");
        System.out.println("3.参数的小数点后第一位=5，正数运算结果为整数部分+1，负数运算结果为整数部分。");
        System.out.println("Math.round(12.4)" + Math.round(12.4));
        System.out.println("Math.round(12.6)" + Math.round(12.6));
        System.out.println("Math.round(12.5)" + Math.round(12.5));
        System.out.println("Math.round(-12.4)" + Math.round(-12.4));
        System.out.println("Math.round(-12.6)" + Math.round(-12.6));
        System.out.println("Math.round(-12.5)" + Math.round(-12.5));
    }

}
