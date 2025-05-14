package executor.threadPoolExecutor;

import java.util.concurrent.*;

/*
4 tasks are running
Queue size is 2
Thread pool -> min thread = 2, max thread = 4;
 */

public class Client {
    public static void main(String[] args) {
//      ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 10, TimeUnit.MINUTES, new ArrayBlockingQueue<>(2), Executors.defaultThreadFactory()., new ThreadPoolExecutor.DiscardPolicy());
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 10,
                                    TimeUnit.MINUTES,
                                    new ArrayBlockingQueue<>(2),
                                    new CustomThreadFactory(),
                                    new CustomRejectHandler());

        executor.allowCoreThreadTimeOut(true);
        /*
        SCENARIOS
        1. 4 tasks -> first two task would be handled by either thread-0 or thread-1
                      other two tasks would be stored in queue for the time being until thread-0 and thread-1 completes their executions

        2. 5 or 6 tasks -> same scenarios happened as in above step and then
                           cpu will check queue size if full and min thread i.e. 2 in this case also acquired so
                           cup will create 2 more threads -> thread-2 and thread-3 in order to execute task5 and task6;

        3. 6+ tasks -> threadPoolExecutor will not accept any other request, because the max capacity is 6;
         */
        // creating 4 tasks
        // creating 6 tasks
        for(int i=1; i<=7; i++){
            // need to submit tasks in order to make tasks executing by threads
            int taskNum = i;
            executor.submit(()-> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Task" + taskNum + " processed by: " + Thread.currentThread().getName());
            });
        }
        executor.shutdown(); // eliminate thread pool shutdown ones tasks get over
    }
}


class CustomThreadFactory implements ThreadFactory{

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setPriority(Thread.NORM_PRIORITY);
        thread.setDaemon(false);
        return thread;
    }
}

class CustomRejectHandler implements  RejectedExecutionHandler{

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("Task rejected: " + r.getClass() + " " + executor.toString());
    }
}