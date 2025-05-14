package executor.threadPoolExecutor.future;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExampleWithMultipleUseCases {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 3, 10, TimeUnit.MINUTES, new ArrayBlockingQueue<>(5), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        // Usecase 1 -> runnable
        // NOTE: Since runnable doesn't return anything so type of Future would be void
        Future<?> futureObject1 = executor.submit(()-> System.out.println("Task1 with runnable"));
        try{
            Object obj = futureObject1.get();
            System.out.println("Type of object in case of runnable is null " + (obj == null));
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Use case 2 -> submit(Runnable runnable, T result)
        List<Integer> listOfInteger = new ArrayList<>();
        Future<List<Integer>> futureObject2 = executor.submit(()->{
            listOfInteger.add(100);
            listOfInteger.add(200);
            System.out.println("Added 100 and 200 to listOfInteger");
        }, listOfInteger);

        try{
            List<Integer> list = futureObject2.get();
            System.out.println("Added numbers are " + listOfInteger.get(0) + " " + listOfInteger.get(1));
        } catch (Exception e){

        }

        // Use case 3 -> using callable
        Future<List<Integer>> futureObject3 = executor.submit(()->{
            System.out.println("Task3 with Callable");
            List<Integer> list = new ArrayList<>();
            list.add(100);
            return list;
        });


        try{
            List<Integer> outputFromFutureObject3 = futureObject3.get();
            System.out.println("Output from future object 3: " + outputFromFutureObject3.get(0));

        } catch (Exception e){};



        executor.shutdown();
    }
}
