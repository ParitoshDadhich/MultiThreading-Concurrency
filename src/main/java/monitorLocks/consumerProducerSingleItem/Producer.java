package monitorLocks.consumerProducerSingleItem;

public class Producer implements Runnable{
    SharedResource sharedResource;
    Producer(SharedResource sharedResource){
        this.sharedResource = sharedResource;
    }

    @Override
    public void run() {
        System.out.println("Producer thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        sharedResource.addItem();
    }
}
