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
    }
}