package monitorLocks.consumerProducerSingleItem;

public class Client {
    public static void main(String[] args) {
        /*
        one way of solving this
         */
//        SharedResource sharedResource = new SharedResource();
//        Thread thread1 = new Thread(new Producer(sharedResource));
//        Thread thread2 = new Thread(new Consumer(sharedResource));
//        thread1.start();
//        thread2.start();
//

       /*

        Another way to solve - MUCH BETTER WAY!
       */
        SharedResource sharedResource = new SharedResource();
        Thread thread1 = new Thread(() -> {
            System.out.println("Producer thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            sharedResource.addItem();
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("Consumer thread: " + Thread.currentThread().getName());
            try {
                sharedResource.consumeItem();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        thread1.start();
        thread2.start();
    }
}
