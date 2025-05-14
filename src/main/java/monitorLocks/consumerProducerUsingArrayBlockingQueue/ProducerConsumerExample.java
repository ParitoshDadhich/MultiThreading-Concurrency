package monitorLocks.consumerProducerUsingArrayBlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProducerConsumerExample {
    public static void main(String[] args) {
        final int CAPACITY = 5;
        final BlockingQueue<String> queue = new ArrayBlockingQueue<>(CAPACITY);

        ExecutorService executor = Executors.newFixedThreadPool(4); // 2 producers + 2 consumers

        // Producer
        Runnable producer = () -> {
            int counter = 1;
            while (true) {
                try {
                    String item = "item" + counter++;
                    queue.put(item); // blocks if queue is full
                    System.out.println(Thread.currentThread().getName() + " produced: " + item);
                    Thread.sleep(1000); // simulate work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        };

        // Consumer
        Runnable consumer = () -> {
            while (true) {
                try {
                    String item = queue.take(); // blocks if queue is empty
                    System.out.println(Thread.currentThread().getName() + " consumed: " + item);
                    Thread.sleep(1500); // simulate work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        };

        // Start producers and consumers
        executor.submit(producer);
        executor.submit(producer);
        executor.submit(consumer);
        executor.submit(consumer);

        // You can optionally shut down after some time
        // executor.shutdown();
    }
}
