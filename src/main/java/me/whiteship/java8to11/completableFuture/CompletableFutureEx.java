package me.whiteship.java8to11.completableFuture;

import java.util.concurrent.*;

public class CompletableFutureEx {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Future<String> futureEx = executorService.submit(() -> "hello");

        // TODO

        futureEx.get();
        // futureEx 에 get 이후에 와야함. get 은 블록킹 콜임
        executorService.shutdown();

        CompletableFuture<String> future1 = new CompletableFuture<>();
        future1.complete("hong");

        System.out.println(future1.get());
        System.out.println("========= future1 end ==========\n");


        CompletableFuture<String> future2 = CompletableFuture.completedFuture("hong");
        System.out.println(future2.get());
        System.out.println("========= future2 end ==========\n");

        /**
         * 리턴타입이 없는 경우
         */
        CompletableFuture<Void> future3 = CompletableFuture.runAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
        });
        future3.get();
        System.out.println("========= future3 end ==========\n");

        /**
         * 리턴타입이 있는 경우
         */
        CompletableFuture<String> future4 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });
        System.out.println(future4.get());
        System.out.println("========= future4 end ==========\n");

        /**
         * 콜백에서 리턴이 있는 경우
         */
        CompletableFuture<String> future5 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).thenApply((s) -> {
            System.out.println(Thread.currentThread().getName());
            return s.toUpperCase();
        });
        System.out.println(future5.get());
        System.out.println("========= future5 end ==========\n");

        /**
         * 콜백에서 리턴이 없는 경우
         */
        CompletableFuture<Void> future6 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).thenAccept((s) -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println(s.toUpperCase());
        });
        future6.get();
        System.out.println("========= future6 end ==========\n");

        /**
         * 콜백에서 리턴을 받을 필요가 없을 경우
         */
        CompletableFuture<Void> future7 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).thenRun(() -> {
            System.out.println(Thread.currentThread().getName());
        });
        future7.get();
        System.out.println("========= future7 end ==========\n");

        /**
         * Executor 를 만들어서 줄 수 있음
         */
        ExecutorService executorService1 = Executors.newFixedThreadPool(4);
        CompletableFuture<Void> future8 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }, executorService1).thenRun(() -> {
            System.out.println(Thread.currentThread().getName());
        });
        future8.get();
        executorService1.shutdown();
        System.out.println("========= future8 end ==========\n");

        ExecutorService executorService2 = Executors.newFixedThreadPool(4);
        CompletableFuture<Void> future9 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }, executorService2).thenRunAsync(() -> {
            System.out.println(Thread.currentThread().getName());
        });
        future9.get();
        executorService2.shutdown();
        System.out.println("========= future9 end ==========\n");
    }
}
