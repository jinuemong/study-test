package com.android.studytest.java.os;

// Race Condition
// 프로세스 혹은 스레드가 동시에 임계 구역의 코드를 실행
// 일관성이 손상되고 데이터 불일치가 발생한다.

public class RaceCondition {
    static int sharedData = 0;

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
                sharedData++; // 공유 데이터 증가
            }
        }
    }

    static class Decrement implements Runnable {
        public void run() {
            for (int i = 0; i < 1000000; i++){
                sharedData--; // 공유 데이터 감소
            }
        }
    }
}
