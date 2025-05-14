package monitorLocks.consumerProducerSingleItem;

public class SharedResource {
    boolean isItemPresent = false;

    public synchronized void addItem(){
        isItemPresent = true;
        System.out.println("Item has been added by " + Thread.currentThread().getName());
        notifyAll(); // notify all thread that are waiting on this monitor object
    }

    public synchronized void consumeItem() throws InterruptedException {
        System.out.println("Item is going to be consumed by " + Thread.currentThread().getName());
        // using while instead of if to avoid "spurious wake-up", sometimes because of system noise
        while(!isItemPresent){
            System.out.println("Item is not present to consume so need to wait until items get added!");
            wait(); // release all the monitor lock - in order to wait and other thread can acquire the monitor lock in order to produce items
        }
        System.out.println("Item has been consumed by " + Thread.currentThread().getName());
        isItemPresent = false;
    }
}
