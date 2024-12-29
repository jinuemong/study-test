package com.android.java.os;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Mutex {
    static int sharedData = 0;
    static Lock lock = new ReentrantLock();

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
                lock.lock();
                try {
                    sharedData++; // 공유 데이터 증가
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    static class Decrement implements Runnable {
        public void run() {
            for (int i = 0; i < 1000000; i++){
                lock.lock();
                try {
                    sharedData--; // 공유 데이터 감소
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
