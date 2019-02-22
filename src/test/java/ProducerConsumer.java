import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumer {
    public static BlockingQueue<String> blockingQueue=new LinkedBlockingQueue<>(10);
    public static class Producer extends Thread{
        @Override
        public void run() {
            while(!isInterrupted()){
                try {
                    blockingQueue.put("product");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println("produce..");
                }
            }
        }
    }

    public static class Consumer extends Thread{
        @Override
        public void run() {
            while(!isInterrupted()){
                try {
                    blockingQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println("consume..");
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread produce=new Producer();
        Thread consume=new Consumer();
        produce.start();
        consume.start();
        try {
            Thread.sleep(1);
            produce.interrupt();
            consume.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

