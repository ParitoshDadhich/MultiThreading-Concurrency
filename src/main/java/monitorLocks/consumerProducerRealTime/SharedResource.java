package monitorLocks.consumerProducerRealTime;

import java.util.LinkedList;
import java.util.Queue;

public class SharedResource {
    private final Queue<String> items = new LinkedList<>();
    private static final int MAX_CAPACITY = 5;
    private int itemCounter = 1;

    public synchronized void produceItems() {
        while (items.size() == MAX_CAPACITY) {
            try {
                System.out.println(Thread.currentThread().getName() + " waiting to produce...");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        String item = "Item" + itemCounter++;
        items.offer(item);
        System.out.println(Thread.currentThread().getName() + " produced: " + item);
        notifyAll();
    }

    public synchronized void consumeItems() {
        while (items.isEmpty()) {
            try {
                System.out.println(Thread.currentThread().getName() + " waiting to consume...");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        String item = items.poll();
        System.out.println(Thread.currentThread().getName() + " consumed: " + item);
        notifyAll();
    }
}
