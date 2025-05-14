package deadLocks;


public class Client {
    public static void main(String[] args) {
        System.out.println("Main thread started " + Thread.currentThread().getName());

        SharedResource sharedResource = new SharedResource();
        Thread th1 = new Thread(()->{
            System.out.println("Thread " + Thread.currentThread().getName());
            sharedResource.produce();
        });

        Thread th2 = new Thread(()->{
            System.out.println("Thread " + Thread.currentThread().getName());
            try{
                Thread.sleep(1000);
                sharedResource.produce();
            } catch (Exception e){}
        });

        th1.start();
        th2.start();
        System.out.println("Suspending th1");
        try{
            Thread.sleep(2000);
        } catch (Exception e){}
        // deadlock condition occured, th1 got suspended and th2 is waiting
        // to get the resource tht was locked/acquired by th1; Now th1 got suspended, th2 will be
        // always be in waiting state;
        // in order to get rid of the state need to resume th1.
        // Because of the nature of suspended -> this method has been deprecated;
        th1.suspend();
        th1.resume();
        System.out.println("Main thread gets completed");
    }
}
