package monitorLocks.consumerProducerMultipleItems;

import java.util.LinkedList;
import java.util.Queue;

public class SharedResource {
    private Queue<String> items = new LinkedList<>();
    private static final int MAX_CAPACITY = 5;

    public synchronized void produceItems(){
        while(items.size() == MAX_CAPACITY){
            try {
                wait();
            } catch (Exception e){
                // handle
            }
        }

        System.out.println("Producing items by " + Thread.currentThread().getName());
        int itemCounter = 1;
        while(items.size() != MAX_CAPACITY){
            items.offer("item" + itemCounter);
            itemCounter++;
        }
        notifyAll();
        System.out.println("Items have been produced and notified by " + Thread.currentThread().getName());
    }

    public synchronized void consumeItems(){
        System.out.println("Items will be consumed by " + Thread.currentThread().getName());

        while(items.isEmpty()){
            System.out.println("Items are not present, need to wait");
            try {
                wait();
            } catch (Exception e){
                // handle
            }
        }

        while(!items.isEmpty()){
            System.out.println("Consumed item: " + items.poll());
        }

        // notifying producer to produce again after consuming, the producer might stay blocked forever if I dont notify.
        notifyAll();
    }
}
