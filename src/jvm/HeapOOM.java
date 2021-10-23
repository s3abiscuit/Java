import java.util.ArrayList;
import java.util.List;

public class HeapOOM {
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        try {
            while (true) {
                list.add(new OOMObject());
            }
        } catch (OutOfMemoryError oom) {
            System.out.println("OOM " + oom);
        }

    }

    static class OOMObject {
    }
}

/* Output:
OOM java.lang.OutOfMemoryError: Java heap space
 */
