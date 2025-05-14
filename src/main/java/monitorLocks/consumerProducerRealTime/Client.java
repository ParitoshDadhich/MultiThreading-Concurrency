package monitorLocks.consumerProducerRealTime;

public class Client {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        // Create multiple producers
        for (int i = 1; i <= 2; i++) {
            Thread producerThread = new Thread(() -> {
                while (true) {
                    sharedResource.produceItems();
                    try {
                        Thread.sleep(1000); // Simulate delay
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }, "Producer-" + i);
            producerThread.start();
        }

        // Create multiple consumers
        for (int i = 1; i <= 2; i++) {
            Thread consumerThread = new Thread(() -> {
                while (true) {
                    sharedResource.consumeItems();
                    try {
                        Thread.sleep(1500); // Simulate delay
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }, "Consumer-" + i);
            consumerThread.start();
        }
    }
}
