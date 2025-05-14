package executor.threadPoolExecutor.future;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.HOURS, new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        executor.allowCoreThreadTimeOut(true);

        // new thread will execute and perform new tasks
        /*
        SUBMIT ACCEPTS THREE TYPES OF PARAMETERS
        public Future<?> submit(Runnable task)
        public <T> Future<T> submit(Runnable task, T result)
        public <T> Future<T> submit(Callable<T> task)
        */
        // WITH RUNNABLE
        /*
        NOTE:
        Inside the submit(), we are passing runnable tasks;
        BASICALLY We are wrapping runnable tasks into Future tasks
         */
        Future<?> futureObj = executor.submit(()-> System.out.println("This is a task that would be executed by thread"));

        futureObj.get();
        // caller is checking the status of thread execution
        System.out.println("Execution status: " + futureObj.isDone());


        // WITH RUNNABLE AND T Result

        executor.shutdown(); // Its a good practice to shutdown the executor ones the task is done, if I dont then ThreadPool executor will be alive and waiting for further tasks to execute
                            // In this case I've set TimeToLive - 1hr so it will be waiting till 1hr for futher tasks, so it's a good practice to shut down gracefully.
    }
}
