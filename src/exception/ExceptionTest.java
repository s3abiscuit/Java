package container;

import java.util.Deque;
import java.util.LinkedList;

public class MinStack {
    private static int min = Integer.MAX_VALUE;
    private static final Deque<Integer> stack = new LinkedList<>();
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        try {
            System.out.println(minStack.pop());
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println(minStack.pop());

    }

    public static void push(int x) {
        if (x <= min) {
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }

    public static int pop() throws Exception {
        if (stack.size() == 0) throw new Exception("size is 0");
        int value = stack.pop();
        if (value == min) {
            min = stack.pop();
        }
        return value;
    }

    public static int top() {
        return stack.peek();
    }

    public static int getMin() {
        return min;
    }
}
