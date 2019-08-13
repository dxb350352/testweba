public class stringInitial {

    public static void main(String[] args) {
        String str1 = "123";
        String str2 = "abc";
        String str = str1 + str2;
        System.out.println(str == str1 + str2);//false
        Integer int1 = new Integer(123);
        Integer int2 = new Integer(0);
        Integer int_ = int1 + int2;
        System.out.println(int_ == int1 + int2);//true
        System.out.println(new stringInitial().toString());

        String str3 = "123" + "abc";//看class文件可知，在编译的时候就变成"123abcd"了

        String s1 = new String("计算机");
        String s2 = s1.intern();
        String s3 = "计算机";
        System.out.println(s1 == s2);
        System.out.println(s3 == s2);

        String str4 = new String("str") + new String("01");
//        String str1 = new String("str01");
        String str5 = str4.intern();
        String str6 = "str01";
        System.out.println(str4 == str5);
        System.out.println(str6 == str5);

        String str7 = str6 + "";
    }


}