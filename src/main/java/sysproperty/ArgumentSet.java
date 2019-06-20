package sysproperty;

public class ArgumentSet {
    /**
     * javac -d . sysproperty/ArgumentSet.java
     * java -Dsystem.property=xc sysproperty.ArgumentSet xxx aaa
     * 输出：
     * program arguments:xxx
     * program arguments:aaa
     * system property:xc
     *
     * @param args
     */
    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println(String.format("program arguments:%s", arg));
        }
        System.out.println(String.format("system property:%s", System.getProperty("system.property")));
    }
}
