package me.whiteship.java8to11.callableFuture;

public class ThreadEx {
    public static void main(String[] args) throws InterruptedException {
        /**
         * Thread 만드는 방법 1
         */
        MyThread myThread = new MyThread();
        myThread.start();
        System.out.println("Hello : " + java.lang.Thread.currentThread().getName());

        /**
         * Thread 만드는 방법 2
         */
        java.lang.Thread thread1 = new java.lang.Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread1 : " + java.lang.Thread.currentThread().getName());
            }
        });
        thread1.start();

        /**
         * Thread 만드는 방법 3 - Java 8 부터
         */
        java.lang.Thread thread2 = new java.lang.Thread(() -> {
            while (true) {
                System.out.println("Thread2 : " + java.lang.Thread.currentThread().getName());
                try {
                    java.lang.Thread.sleep(1000L); // Thread.sleep()을 하면 다른 Thread 에 우선권이 넘어감
                } catch (InterruptedException e) {
                    System.out.println("exit!");
                    return; //run() 의 반환 타입이 void 로 return; 을 하면 실행이 종료됨
                }
            }
        });
        thread2.start();

        thread2.sleep(3000L);
        thread2.interrupt(); // interrupt() 는 종료하는 게 아님, InterruptedException 으로 보내서 return 으로 종료 시켜야함


        java.lang.Thread thread3 = new java.lang.Thread(() -> {
                System.out.println("Thread3 : " + java.lang.Thread.currentThread().getName());
                try {
                    java.lang.Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
        });
        thread3.start();

        try {
            thread3.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(thread3.getName() + " is finished");
    }

    /**
     * Thread 만드는 방법 1
     */
    static class MyThread extends java.lang.Thread {
        @Override
        public void run() {
            System.out.println("Thread0 : " + java.lang.Thread.currentThread().getName());
        }
    }

}
