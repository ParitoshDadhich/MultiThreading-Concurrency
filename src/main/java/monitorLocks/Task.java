package monitorLocks;

public class Task {

    public synchronized void task1() throws InterruptedException {
        System.out.println("Task-1, inside synchronized method and current thread:  " + Thread.currentThread().getName());
        Thread.sleep(5000);
    }

    public void task2(){
        System.out.println("Task-2, not inside synchronized method and current thread: " + Thread.currentThread().getName());
        synchronized (this){
            System.out.println("Task-2 and inside synchronized block");
        }
    }

    public void task3(){
        System.out.println("Task-3, not a synchronized block/method");
    }
}
