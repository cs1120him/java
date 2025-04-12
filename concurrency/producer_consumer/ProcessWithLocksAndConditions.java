package concurrency.producer_consumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProcessWithLocksAndConditions {
    private static Lock l = new ReentrantLock();
    private static Condition notFull = l.newCondition();
    private static Condition notEmpty = l.newCondition();
    private static Queue<Integer> queue = new LinkedList<>();
    
    public void produce() throws InterruptedException {
        while(true) {
            // To make produce more realistically
            Random rand = new Random();
            int randomInt = rand.nextInt(500);

            Thread.sleep(randomInt);
            l.lock();

            // Tells the current lock acquire count for the current thread running this.
            System.out.println("Lock hold count after acquire in produce: " + ((ReentrantLock) l).getHoldCount());
            
            int s = queue.size();
            if (s == 5) {
                // await releases the lock completely, 'N' hold count to 0, so that the other thread can acquire the lock.
                // However, when this thread again acquires the lock, the prev count is restored.
                notFull.await(); 
            } else {
                System.out.println("Adding: " + s);
                queue.add(s);
                notEmpty.signal();
            }
            l.unlock();
        }
    }

    public void consume() throws InterruptedException {
        while(true) {
            Random rand = new Random();
            int randomInt = rand.nextInt(500);

            Thread.sleep(randomInt + 250);
            l.lock();

            // Tells the current lock acquire count for the current thread running this.
            System.out.println("Lock hold count after acquire in consume: " + ((ReentrantLock) l).getHoldCount());
            
            int s = queue.size();
            if (s == 0) {
                notEmpty.await();
            } else {
                System.out.println("Removing: " + queue.remove());
                notFull.signal();
            }
            l.unlock();
        }
    }
}