package com.android.java.os;

import java.util.concurrent.Semaphore;

public class Semapho {
    static int sharedData = 0;
    static Semaphore semaphore = new Semaphore(1);

    public static void main(String[] args){
        Thread thread1 = new Thread(new Increment());
        Thread thread2 = new Thread(new Decrement());

        thread1.start();
        thread2.start();

        try {
            // 종료 대기
            thread1.join();
            thread2.join();
        } catch (InterruptedException e){
            System.out.println(e.getMessage());
        }

        System.out.println("Final value of sharedData: " + sharedData);
    }

    static class Increment implements Runnable {
        public void run() {
            for (int i = 0; i < 1000000; i++){
                try {
                    semaphore.acquire(); // 세마포어 획득
                    sharedData++; // 공유 데이터 증가
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    semaphore.release(); // 세마포어 해제
                }
            }
        }
    }

    static class Decrement implements Runnable {
        public void run() {
            for (int i = 0; i < 1000000; i++){
                try {
                    semaphore.acquire(); // 세마포어 획득
                    sharedData--; // 공유 데이터 감소
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    semaphore.release(); // 세마포어 해제
                }
            }
        }
    }
}
