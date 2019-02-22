
import org.junit.Test;

import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest {

    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (!isInterrupted()) {
                }
                System.out.println("finished");
            }
        };
        thread.start();
        thread.interrupt();
        System.out.println("hhh");
    }

    public static class Reentrant{
        private Lock lock=new ReentrantLock();
        private int inc=0;

        public int getInc() {
            return inc;
        }

        public void increment(){
            inc++;
        }
    }


    @Test
    public void ThreadPoolTest() throws InterruptedException {
        //CountDownLatch countDownLatch = new CountDownLatch(4);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2, 4, 1000, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(2));
        for (int i = 0; i < 4; i++) {
            executor.execute(()->{
                System.out.println("run..");
                //countDownLatch.countDown();
            });
        }
        //countDownLatch.await();
        System.out.println("END");
        executor.shutdown();

    }

    @Test
    public void InterruptTest() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (!isInterrupted()) {
                }
                System.out.println("finished");
            }
        };
        thread.start();
        thread.interrupt();
        System.out.println("hhh");
    }
}

class MyTask implements Runnable {
    private int taskNum;

    public MyTask(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void run() {
        System.out.println("开始task " + taskNum);
        try {
            Thread.currentThread().sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("结束task " + taskNum);
    }

}

class MyThread extends Thread {

}