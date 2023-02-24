package me.whiteship.java8to11.callableFuture;

import java.util.concurrent.*;

public class ExecutorsEx {
    public static void main(String[] args) {
        // ScheduledExecutorService
        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        executorService.execute(() -> System.out.println("Thread1 : " + Thread.currentThread().getName()));
        executorService.submit(() -> {
            System.out.println("Thread1 : " + Thread.currentThread().getName());
        });

        executorService.shutdown(); // 코드를 다 실행하고 shutdown 시킴
//        executorService.shutdownNow();

        ExecutorService executorService1 = Executors.newFixedThreadPool(2);
        executorService1.submit(getRunnable("Hello"));
        executorService1.submit(getRunnable("Hong"));
        executorService1.submit(getRunnable("The"));
        executorService1.submit(getRunnable("Java"));
        executorService1.submit(getRunnable("!!"));
        executorService1.shutdown();

        ScheduledExecutorService executorService2 = Executors.newSingleThreadScheduledExecutor();
        executorService2.schedule(getRunnable("Hello"), 1, TimeUnit.SECONDS);
        executorService2.scheduleAtFixedRate(getRunnable("Hello"), 1, 2, TimeUnit.SECONDS);
    }

    private static Runnable getRunnable(String message) {
        return () -> System.out.println(message + " " + Thread.currentThread().getName());
    }
}
