package concurrency.producer_consumer;

import java.util.LinkedList;
import java.util.Queue;

public class Process {
    private static Queue<Integer> queue = new LinkedList<>();
    
    public void produce() throws InterruptedException {
        synchronized(this) {
            while(true) {
                int s = queue.size();
                if (s == 5) {
                    this.notify();
                    this.wait();
                } else {
                    Thread.sleep(500);
                    System.out.println("Adding: " + s);
                    queue.add(s);
                }
            }
        }
    }

    public void consume() throws InterruptedException {
        synchronized(this) {
            while(true) {
                int s = queue.size();
                if (s == 0) {
                    this.notify();
                    this.wait();
                } else {
                    Thread.sleep(500);
                    System.out.println("Removing: " + queue.remove());
                }
            }
        }
    }
}