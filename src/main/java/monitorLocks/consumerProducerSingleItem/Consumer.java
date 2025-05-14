package monitorLocks.consumerProducerSingleItem;

public class Consumer implements Runnable{
    SharedResource sharedResource;
    Consumer(SharedResource sharedResource){
        this.sharedResource = sharedResource;
    }

    @Override
    public void run() {
        System.out.println("Consumer thread: " + Thread.currentThread().getName());
        try {
            sharedResource.consumeItem();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
