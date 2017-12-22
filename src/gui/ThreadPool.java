package gui;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by ajay on 5/29/2016.
 */
public class ThreadPool {
    static ExecutorService executorService = Executors.newFixedThreadPool(2);
    static volatile boolean isTaskComplete = false;
    public ExecutorService getExecutorService(){
        return executorService;
    }
    public static void executeTask(Runnable task) throws InterruptedException {
        executorService.execute(task);
        //executorService.awaitTermination(100, TimeUnit.SECONDS);
        isTaskComplete = true;
        System.out.println("Execution compete.");

    }
}
