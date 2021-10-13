package concurrent.aqs;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    public static void main(String[] args) {
        int N = 8;            //工人数
        // 限制同时执行的线程数量
        Semaphore semaphore = new Semaphore(5); //机器数目
        for(int i=0;i<N;i++)
            new Worker(i,semaphore).start();
    }

    static class Worker extends Thread{
        private int num;
        private Semaphore semaphore;
        public Worker(int num,Semaphore semaphore){
            this.num = num;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("工人"+this.num+"占用一个机器在生产...");
                Thread.sleep(2000);
                System.out.println("工人"+this.num+"释放出机器");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
/*
工人1占用一个机器在生产...
工人2占用一个机器在生产...
工人3占用一个机器在生产...
工人0占用一个机器在生产...
工人5占用一个机器在生产...
工人0释放出机器
工人5释放出机器
工人2释放出机器
工人1释放出机器
工人7占用一个机器在生产...
工人4占用一个机器在生产...
工人3释放出机器
工人6占用一个机器在生产...
工人6释放出机器
工人4释放出机器
工人7释放出机器
 */
