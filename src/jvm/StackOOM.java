public class StackOOM {
    public static void main(String[] args) {
        try {
            new StackOOM().stackOOMTest();
        } catch (OutOfMemoryError e) {
            System.out.println("OOM " + e);
        }
    }

    private void forever(){
        while (true) ;
    }

    public void stackOOMTest() {
        while (true) {
            new Thread(){
                @Override
                public void run() {
                    forever();
                }
            }.start();
        }
    }
}

/* output:
OOM java.lang.OutOfMemoryError: unable to create new native thread
 */