public class StackOverFlow {
    public static void main(String[] args) {
        StackOverFlow sof = new StackOverFlow();
        try {
            sof.recursion();
        } catch (Throwable e) {
            System.out.println("stackDepth is " + sof.stackDepth + " " + e);
        }
    }

    private int stackDepth = 1;

    public void recursion() {
        stackDepth++;
        recursion();
    }
}
