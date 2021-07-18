package concurrent.sync;

public class VolatileTest {
    public static void main(String[] args) {
        try {
            RunThread thread = new RunThread();
            thread.start();
            Thread.sleep(1000);
            thread.setRunning(false);
            System.out.println("已经赋值为false");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static class RunThread extends Thread {

        // 使用volatile关键字确保一个线程做了修改另外一个线程立即可见
        // 如果没有volatile关键字RunThread会一直循环下去
        volatile private boolean isRunning = true;

        public boolean isRunning() {
            return isRunning;
        }

        public void setRunning(boolean isRunning) {
            this.isRunning = isRunning;
        }

        @Override
        public void run() {
            System.out.println("进入run了");
            while (isRunning) {
                System.out.println("working");
            }
            System.out.println("线程被停止了！");
        }

    }

}

