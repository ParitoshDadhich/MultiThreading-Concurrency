package customLocks.ReadWriteLock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SharedResource {
    public void produce(ReadWriteLock readWriteLock){
        try {
            readWriteLock.readLock().lock();
            System.out.println("Acquired the read lock by " + Thread.currentThread().getName());
            System.out.println("Reading the produce resource by " + Thread.currentThread().getName());
            Thread.sleep(4000);
        } catch (Exception e){

        } finally {
            readWriteLock.readLock().unlock();
            System.out.println("Released the read lock by " + Thread.currentThread().getName());
        }
    }

    public void consume(ReadWriteLock readWriteLock){
        try{
            readWriteLock.writeLock().lock();
            System.out.println("Acquired write lock by " + Thread.currentThread().getName());
            System.out.println("Writing ");
            Thread.sleep(2000);
        } catch (Exception e){

        }
        finally {
            readWriteLock.writeLock().unlock();
            System.out.println("Released write lock by " + Thread.currentThread().getName());
        }
    }
}
