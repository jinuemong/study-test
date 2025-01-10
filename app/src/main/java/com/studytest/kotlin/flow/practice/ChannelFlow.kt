package com.studytest.kotlin.flow.practice

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch

data class User(val name: String)
interface UserApi {
    suspend fun takePage(pageNumber: Int): List<User>
}
// Flow는 콜드 데이터 스트림으로, 필요할 때만 값을 생성합니다.
// 특정 상황 : 사용자가 첫 페이지에 있는 경우
// -> 더 많은 페이지를 요청할 필요없이 첫 값만 요청
// -> 필요할 때만 지연 요청
// -> 콜드 데이터 스트림 필요

// 반면에 원소를 처리하고 있을 때 미리 페이지를 받아오고 싶은 경우가 있습니다.
// - 데이터를 생성하고 소비하는 과정이 별개로 진행
// -> 이 경우 핫 데이터 스트림 + 콜드 데이터 스트림 필요
// 채널플로우 빌더 활용 : collect와 같은 일반 함수로 최종 연산으로 시작됨
// 한 번 시작하면, 리시버를 기다릴 필요 없이 분리된 코루틴에서 값을 생성 (채널의 특징)
// -> 페이지를 얻어오면서 동시에 사용자 확인
fun allUserFlow(api: UserApi): Flow<User> = channelFlow {
    var page = 0
    do {
        val users = api.takePage(page++)
        users.forEach { send(it) } //emit 대신 send 활용
    } while (users.isNotEmpty())
}

// 채널 플로우는 여러 개의 값을 독립적으로 계산해야 할 때 주로 사용합니다.
// 다른 코루틴처럼 channelFlow도 모든 자식 코루틴이 종료 상태가 될 때까지 끝나지 않습니다.
