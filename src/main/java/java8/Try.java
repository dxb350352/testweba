package java8;

import java.io.DataInputStream;
import java.io.FileInputStream;

public class Try {

    public static void main(String args[]) {
        try (
                FileInputStream fis = new FileInputStream("wwww");
                DataInputStream dis = new DataInputStream(fis);
        ) {

        } catch (Exception e) {

        }
    }
}
