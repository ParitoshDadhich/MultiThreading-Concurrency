package customLocks.ReadWriteLock;


import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
WHEN TO USE READWRTIELOCK?
When the application is more read extensive then write extensive
 */

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        SharedResource resource1 = new SharedResource();

        Thread th1 = new Thread(()->{
            System.out.println("Thread " + Thread.currentThread().getName());
            resource1.produce(readWriteLock);
        });

        SharedResource resource2 = new SharedResource();
        Thread th2 = new Thread(() -> {
            System.out.println("Thread " + Thread.currentThread().getName());
            resource2.produce(readWriteLock);
        });

        SharedResource resource3 = new SharedResource();
        Thread th3 = new Thread(()->{
            System.out.println("Thread " + Thread.currentThread().getName());
            // write lock will only acquire when read lock gets released;
            resource3.consume(readWriteLock);
        });

        th1.start();
        th2.start();
        th3.start();
    }
}
