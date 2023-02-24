package me.whiteship.java8to11.completableFuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class CompletableFutureEx2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        /**
         * 두 CompletableFuture 가 연관관계가 있는 경우
         */
        CompletableFuture<String> hello1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });
//        CompletableFuture<String> world1 = CompletableFuture.supplyAsync(() -> {
//            System.out.println("World " + Thread.currentThread().getName());
//            return "World";
//        });

        // 이전에는 서로 연결하는 방법이 없어서 get 을 둘다 해줬어야함
//        hello1.get();
//        world1.get();
        // 이전 방법 끝

        CompletableFuture<String> future1 = hello1.thenCompose(CompletableFutureEx2::getWorld);
        System.out.println(future1.get());
        System.out.println("========== future1 end ==========\n");

        /**
         * 두 CompletableFuture 가 연관관계가 없는 경우
         */
        CompletableFuture<String> hello2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });
        CompletableFuture<String> world2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return "World";
        });

        CompletableFuture<String> future2 = hello2.thenCombine(world2, (h2, w2) -> h2 + " " + w2);
        System.out.println(future2.get());
        System.out.println("========== future2 end ==========\n");

        /**
         * 두 CompletableFuture 가 연관관계가 없는 경우2
         */
        CompletableFuture<String> hello3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });
        CompletableFuture<Integer> world3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return 100;
        });

        List<CompletableFuture> future3 = Arrays.asList(hello3, world3);

        CompletableFuture[] futuresArray = future3.toArray(new CompletableFuture[future3.size()]);
        CompletableFuture<List<Object>> results = CompletableFuture.allOf(futuresArray)
                .thenApply(v -> future3.stream()
                            .map(CompletableFuture::join)
                            .collect(Collectors.toList()));

        results.get().forEach(System.out::println);
        System.out.println("========== future3 end ==========\n");


        /**
         * 두 CompletableFuture 중 먼저오는 걸 출력
         */
        CompletableFuture<String> hello4 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });
        CompletableFuture<String> world4 = CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return "World";
        });

        CompletableFuture<Void> future4 = CompletableFuture.anyOf(hello4, world4).thenAccept(System.out::println);
        future4.get();
        System.out.println("========== future4 end ==========\n");

        /**
         * CompletableFuture 예외 처리1
         */
        boolean throwError1 = true;
        CompletableFuture<String> hello5 = CompletableFuture.supplyAsync(() -> {
            if (throwError1) {
                throw new IllegalArgumentException();
            }
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).exceptionally(ex -> {
            System.out.println(ex);
            return "Error!";
        });
        System.out.println(hello5.get());
        System.out.println("========== hello5 end ==========\n");

        /**
         * CompletableFuture 예외 처리 2(handle 은 정상과 에러의 경우를 두개 다 받아서 처리)
         */
        boolean throwError2 = false;
        CompletableFuture<String> hello6 = CompletableFuture.supplyAsync(() -> {
            if (throwError2) {
                throw new IllegalArgumentException();
            }
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).handle((result, ex) -> {
            if(ex != null){
                System.out.println(ex);
                return "ERROR";
            }
            return result;
        });

        System.out.println(hello6.get());
        System.out.println("========== hello6 end ==========\n");

    }

    private static CompletableFuture<String> getWorld(String message) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return message + "World";
        });
    }
}
