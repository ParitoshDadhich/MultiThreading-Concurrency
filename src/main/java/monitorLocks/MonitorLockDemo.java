package monitorLocks;

public class MonitorLockDemo {
    public static void main(String[] args) {
        Task taskObj = new Task();
        Thread thread1 = new Thread(() -> {
            try {
                taskObj.task1();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread thread2 = new Thread(() -> taskObj.task2());
        Thread thread3 = new Thread(() -> taskObj.task3());

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
