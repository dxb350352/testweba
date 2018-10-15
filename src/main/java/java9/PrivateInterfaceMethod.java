package java9;

public interface PrivateInterfaceMethod {
    private void init() {
        System.out.println("Initializing");
    }

    default void def() {
        init();
        System.out.println("Default");
    }

    static void main(String[] args) {
        PrivateInterfaceMethod pim = new PrivateInterfaceMethod() {
        };
        pim.init();
        pim.def();
    }
}
