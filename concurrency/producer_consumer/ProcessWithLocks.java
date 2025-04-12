package concurrency.producer_consumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProcessWithLocks {
    private static Lock l = new ReentrantLock();
    private static Queue<Integer> queue = new LinkedList<>();
    
    public void produce() throws InterruptedException {
        while(true) {
            // To make produce more realistically
            Random rand = new Random();
            int randomInt = rand.nextInt(500);

            Thread.sleep(randomInt + 250);
            l.lock();
            int s = queue.size();
            if (s == 5) {
                l.unlock(); 
            } else {
                System.out.println("Adding: " + s);
                queue.add(s);
                l.unlock();
            }
        }
    }

    public void consume() throws InterruptedException {
        while(true) {
            Random rand = new Random();
            int randomInt = rand.nextInt(500);

            Thread.sleep(randomInt + 250);
            l.lock();
            int s = queue.size();
            if (s == 0) {
                l.unlock();
            } else {
                System.out.println("Removing: " + queue.remove());
                l.unlock();
            }
        }
    }
}