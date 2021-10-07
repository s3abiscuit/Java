package algs.array;

import java.util.Arrays;

public class BasicOperation {
    private static final int[] integers = {2, 7, 11, 15};

    public static void main(String[] args) {

        // Arrays.copyOf
        int[] copy1 = Arrays.copyOf(integers, integers.length - 1);
        System.out.println(Arrays.toString(copy1));

        // System.arraycopy
        int[] copy2 = new int[integers.length - 1];
        System.arraycopy(integers, 0, copy2, 0, integers.length - 1);
        System.out.println(Arrays.toString(copy2));

    }

}
