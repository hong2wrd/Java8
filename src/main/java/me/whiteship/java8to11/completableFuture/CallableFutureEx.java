package me.whiteship.java8to11.completableFuture;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class CallableFutureEx {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService1 = Executors.newSingleThreadExecutor();

        Callable<String> hello = () -> {
            Thread.sleep(2000L);
            return "Hello";
        };

        Future<String> helloFuture = executorService1.submit(hello);
        System.out.println(helloFuture.isDone()); // inDone() 상태확인
        System.out.println("Started!");

        helloFuture.get();
        helloFuture.cancel(true); // true 는 interrupted 로 종료 하지만 false 를 해도 값은 가져올 수 없음

        System.out.println(helloFuture.isDone()); //cancel 을 하면 무조건 true 임
        System.out.println("End!!");
        executorService1.shutdown();


        ExecutorService executorService2 = Executors.newSingleThreadExecutor();
        Callable<String> hi = () -> {
            Thread.sleep(2000L);
            return "Hi";
        };
        Callable<String> java = () -> {
            Thread.sleep(3000L);
            return "Java";
        };
        Callable<String> hong = () -> {
            Thread.sleep(1000L);
            return "Hong";
        };
        List<Future<String>> futures1 = executorService2.invokeAll(Arrays.asList(hi, java, hong)); // invokeAll 리스트에 있는 걸 다 기다리고 한번에 내보내줌
        for (Future<String> f : futures1) {
            System.out.println(f.get());
        }
        executorService2.shutdown();

        ExecutorService executorService3 = Executors.newFixedThreadPool(4);
        Callable<String> hi1 = () -> {
            Thread.sleep(2000L);
            return "Hi1";
        };
        Callable<String> java1 = () -> {
            Thread.sleep(3000L);
            return "Java1";
        };
        Callable<String> hong1 = () -> {
            Thread.sleep(1000L);
            return "Hong1";
        };
        String s = executorService3.invokeAny(Arrays.asList(hi, java, hong)); //invokeAny 는 여러 작업 중 하나라도 응답이 오면 종료
        System.out.println(s);

        executorService3.shutdown();

    }
}

