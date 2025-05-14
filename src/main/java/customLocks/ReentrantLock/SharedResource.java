package customLocks.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class SharedResource {
    private boolean isAvailable = false;

    /*
          NOTE
          In case of Reentrant lock, threads doesn't depend upon the object
          It depends upon the lock of type reentrantLock that is being passed/present;

          Due to this multiple threads that are depend upon multiple different object can be blocked
          to enter into shared resource(methods, blocks etc);
     */
    public void produce(ReentrantLock reentrantLock){
        try{
            reentrantLock.lock();
            System.out.println("Lock has been acquired by " + Thread.currentThread().getName());
            isAvailable = true;
            Thread.sleep(2000);
        } catch (Exception e){

        } finally {
            reentrantLock.unlock();
            System.out.println("Lock has been released by " + Thread.currentThread().getName());
        }
    }
}
