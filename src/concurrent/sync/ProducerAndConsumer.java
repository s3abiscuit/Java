package concurrent.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerAndConsumer {
    public static void main(String[] args) {
        Factory factory = new Factory();  // use wait notify
//        Factory1 factory = new Factory1();   // use lock condition
        new Thread(new Runnable() {
            @Override
            public void run() {
                factory.produce("product");
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                factory.consume();
            }
        }).start();
    }

    private static class Factory {
        private List<String> list = new ArrayList<>();

        public void produce(String str) {
            while (true) {
                synchronized (list) {
                    if (list.size() == 0) {
                        System.out.println("producer");
                        list.add(str);
                        list.notify();
                    } else {
                        try {
                            list.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        public void consume() {
            while (true) {
                synchronized (list) {
                    if (list.size() != 0) {
                        System.out.println("consumer");
                        list.remove(0);
                        list.notify();
                    } else {
                        try {
                            list.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private static class Factory1 {
        private List<String> list = new ArrayList<>();
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        public void produce(String str) {
            while (true) {
                lock.lock();
                if (list.size() == 0) {
                    System.out.println("producer");
                    list.add(str);
                    condition.signal();
                } else {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.unlock();
            }
        }

        public void consume() {
            while (true) {
                lock.lock();
                if (list.size() != 0) {
                    System.out.println("consumer");
                    list.remove(0);
                    condition.signal();
                } else {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.unlock();
            }
        }
    }
}


