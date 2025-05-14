package deadLocks;

import java.security.spec.ECField;

public class SharedResource {
    private boolean isItemAvailable = false;
    public synchronized void produce(){
        System.out.println("Acquired lock by " + Thread.currentThread().getName());

        isItemAvailable = true;
        try{
            Thread.sleep(8000);
        } catch (Exception e){}

        System.out.println("Lock released by " + Thread.currentThread().getName());
    }
}
