package com.android.java.oop.dcd;

// 메서드를 통해서만 객체의 내부 상태에 접근
// 이는 캡슐화를 지키고 있다고 보기 어렵다
// 접근자와 수정자에 과도하게 의존하는 설계 방식으로
// 추측에 의한 설계 전략이라고 한다.
//  막 연 한 추 측 을 기 반 으 로 설 계 를 진 행

public class Movie {
    public Money fee;

    public Money getFee() {
        return fee;
    }

    public void setFee(Money fee){
        this.fee = fee;
    }
}
