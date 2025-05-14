package monitorLocks.consumerProducerMultipleItems;

public class Client {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();
        Thread producerThread = new Thread(() -> {
            System.out.println("Producer thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(5000);
            } catch (Exception e){
                // logic
            }
            sharedResource.produceItems();
        });

        Thread consumerThread = new Thread(() -> {
            System.out.println("Consumer thread: " + Thread.currentThread().getName());
            sharedResource.consumeItems();
        });

        producerThread.start();
        consumerThread.start();
    }
}
