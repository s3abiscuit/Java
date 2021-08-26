package generics;

public class GenericsTest {
    public static void main(String[] args) {
        testObjectArray();
        testGenericsClass();
        testExtends();
        testSuper();
    }

    private static void testObjectArray() {
        Object[] arr = new Object[]{1, "hello", 3.14f};
        for (Object o : arr) {
            if (o instanceof Integer) System.out.println((int) o);
            if (o instanceof String) System.out.println((String) o);
            if (o instanceof Float) System.out.println((float) o);
        }
    }

    private static void testGenericsClass() {
        Pair<String> p = new Pair<>("hello", "world");
        Pair<Integer> p1 = new Pair<>(111, 222);
        System.out.println(p);
//        p.setFirst(1); compile error
        p.setFirst("good");
        p.setLast("morning");
        System.out.println(p);
    }

    private static void testExtends() {
        Pair<Integer> p1 = new Pair<>(123, 456);
        Pair<Float> p2 = new Pair<>(12.3f, 45.6f);
        int n1 = add(p1);
        int n2 = add(p2);
        System.out.println(n1);
        System.out.println(n2);
    }

    static int add(Pair<? extends Number> p) {
        Number first = p.getFirst();
        Number last = p.getLast();
        // compile error
        // reason: 可以传入 float
//        p.setFirst(new Integer(first.intValue() + 100));
//        p.setLast(new Integer(last.intValue() + 100));
        return first.intValue() + last.intValue();
    }

    private static void testSuper() {
        Pair<Number> p1 = new Pair<>(12.3, 4.56);
        setSame(p1, 100);
        System.out.println(p1.getFirst() + ", " + p1.getLast());

        Number number = 12.3;
        number = 100;
        System.out.println(number);
    }

    static void setSame(Pair<? super Integer> p, Integer n) {
        p.setFirst(n);
        p.setLast(n);
    }

}

class Pair<T> {
    private T first;
    private T last;

    public Pair(T first, T last) {
        this.first = first;
        this.last = last;
    }
//    public Pair() {
//        this.first = new T();
//        this.last = new T();
//    }
    public T getFirst() {
        return first;
    }

    public T getLast() {
        return last;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setLast(T last) {
        this.last = last;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "first=" + getFirst() +
                ", last=" + getLast() +
                '}';
    }
}
