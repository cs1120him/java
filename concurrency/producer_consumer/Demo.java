package concurrency.producer_consumer;

public class Demo {
    public static void main(String args[]) throws InterruptedException {
        Process p1 = new Process();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    p1.produce();
                } catch (InterruptedException e) {
                    System.out.println("Thread1 interrupted by main thread");
                    e.printStackTrace();
                }
            } 
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    p1.consume();
                } catch (InterruptedException e) {
                    System.out.println("Thread2 interrupted by main thread");
                    e.printStackTrace();
                }
            } 
        });

        t1.start();
        t2.start();

        Thread.sleep(10000);
        t1.interrupt();
        t2.interrupt();

        ProcessWithLocks p2 = new ProcessWithLocks();

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    p2.produce();
                } catch (InterruptedException e) {
                    System.out.println("Thread3 interrupted by main thread");
                    e.printStackTrace();
                }
            } 
        });

        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    p2.consume();
                } catch (InterruptedException e) {
                    System.out.println("Thread4 interrupted by main thread");
                    e.printStackTrace();
                }
            } 
        });

        t3.start();
        t4.start();

        Thread.sleep(10000);
        t3.interrupt();
        t4.interrupt();

        ProcessWithLocksAndConditions p3 = new ProcessWithLocksAndConditions();

        Thread t5 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    p3.produce();
                } catch (InterruptedException e) {
                    System.out.println("Thread5 interrupted by main thread");
                    e.printStackTrace();
                }
            } 
        });

        Thread t6 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    p3.consume();
                } catch (InterruptedException e) {
                    System.out.println("Thread6 interrupted by main thread");
                    e.printStackTrace();
                }
            } 
        });

        t5.start();
        t6.start();

        Thread.sleep(15000);
        t5.interrupt();
        t6.interrupt();
    }
}