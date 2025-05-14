package customLocks.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;
/*
      NOTE
      In case of Reentrant lock, threads doesn't depend upon the object
      It depends upon the lock of type reentrantLock that is being passed/present;

      Due to this multiple threads that are depend upon multiple different object can be blocked
      to enter into shared resource(methods, blocks etc);
 */
public class ReentrantLockDemo {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        SharedResource resource1 = new SharedResource();
        Thread th1 = new Thread(()->{
            System.out.println("Thread: " + Thread.currentThread().getName());
            resource1.produce(reentrantLock);
        });

        SharedResource resource2 = new SharedResource();
        Thread th2 = new Thread(() -> {
            System.out.println("Thread: " + Thread.currentThread().getName());
            try{
                Thread.sleep(4000);
            } catch (Exception e){}
            resource2.produce(reentrantLock);
        });

        th1.start();
        th2.start();
    }
}
