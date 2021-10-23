import java.util.ArrayList;
import java.util.List;

public class ConstantPoolOOM {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        int i = 0;
        while (true) {
            list.add((i++ + "").intern());
        }
    }
}

/* Output:
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	at ConstantPoolOOM.main(ConstantPoolOOM.java:9)
 */
