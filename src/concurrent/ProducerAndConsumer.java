package concurrent;

import java.util.ArrayList;
import java.util.List;

public class ProducerAndConsumer {
    public static void main(String[] args) {
        Factory factory = new Factory();
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
}

class Factory {
    private List<String> list = new ArrayList<>();
    public void produce(String str){
        while(true){
            synchronized (list){
                if(list.size()==0){
                    System.out.println("producer");
                    list.add(str);
                    list.notify();
                }else{
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void consume(){
        while(true){
            synchronized (list){
                if(list.size()!=0){
                    System.out.println("consumer");
                    list.remove(0);
                    list.notify();
                }else{
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
