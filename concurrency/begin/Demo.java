package concurrency.begin;

public class Demo {
    public static void main(String args[]) {
        Thread t1 = new Thread(new Concurrent());
        Thread t2 = new Thread(new Concurrent());
        Thread t3 = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(i);
                }
            }
            
        });

        t1.start();
        t2.start();
        t3.start();
    }
}