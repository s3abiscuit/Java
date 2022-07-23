package basics;

import java.nio.charset.StandardCharsets;

public class PrimitiveType {
    public static void main(String[] args) {
        String str = "a";
        byte[] sss = str.getBytes(StandardCharsets.UTF_8);
        System.out.println(sss[0]); // 97
    }
}
