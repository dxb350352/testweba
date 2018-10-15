package java10;

import java.util.ArrayList;

public class Var {
    public static void main(String[] args) {
        var list = new ArrayList<String>();
        list.add("1");
        var str = list.get(0);
        System.out.println(str instanceof String);
    }
}
