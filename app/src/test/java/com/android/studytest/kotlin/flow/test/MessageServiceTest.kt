package com.android.studytest.kotlin.flow.test

import com.android.studytest.utils.CoroutinesTestExtension
import com.studytest.kotlin.flow.test.Message
import com.studytest.kotlin.flow.test.MessageService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.time.Duration.Companion.milliseconds

// runTest를 사용하는 경우 스코프는 this가 아닌 backgroundScope가 돕니다.
// 이는 테스트에서 스코프가 끝나는 것을 기다릴 수 없음,
// 상태플로우와 공유플로우는 무한정이기 때문에 스코프가 취소되지 않는 한 플로우가 종료되지 않습니다.
@ExperimentalCoroutinesApi
@ExtendWith(
    CoroutinesTestExtension::class
)
class MessageServiceTest {
    // toList 활용 -> 테스트가 끝나지 않음
    // take 활용 -> 정확한 테스트 불가능
    @Test
    fun `should emit messages from user`() = runTest {
        // given
        val source = flowOf(
            Message(fromUserId = "0", message = "A"),
            Message(fromUserId = "1", message = "B"),
            Message(fromUserId = "0", message = "C"),
        )
        val service = MessageService(
            messageSource = source,
            scope = backgroundScope
        )

        // when
        val result = service.observeMessage("0")
            // take를 호출하면 테스트가 통과하지만, 많은 정보를 잃습니다.
            .take(2)
            .toList() // 영원히 기다리게 됨 (공유 플로우 특징)


        // then
        Assertions.assertEquals(
            listOf(
                Message(fromUserId = "0", message = "A"),
                Message(fromUserId = "0", message = "C"),
            ), result
        )
    }

    // backgroundScope에서 플로우를 시작하고, 플로우가 방출하는 모든 원소를 컬렉션에 저장
    // 실패하는 경우 무엇인지, 무엇이 되어야 하는지에 대해 명확해짐
    // 테스트 시간을 유연하게 설정 가능
    @Test
    fun `should emit message from user`() = runTest {
        //given
        val source = flow {
            emit(Message(fromUserId = "0", message = "A"))
            delay(1000)
            emit(Message(fromUserId = "1", message = "B"))
            emit(Message(fromUserId = "0", message = "C"))
        }
        val service = MessageService(
            messageSource = source,
            scope = backgroundScope
        )

        // when
        val emittedMessages = mutableListOf<Message>()
        service.observeMessage("0")
            .onEach { emittedMessages.add(it) }
            .launchIn(backgroundScope) // scope 종속
        delay(1)

        // then
        Assertions.assertEquals(
            listOf(
                Message(fromUserId = "0", message = "A")
            ), emittedMessages
        )
    }

    // ListDuringForFlowTest
    @Test
    fun `should emit message from user with Extension`() = runTest {
        //given
        val source = flow {
            emit(Message(fromUserId = "0", message = "A"))
            emit(Message(fromUserId = "1", message = "B"))
            emit(Message(fromUserId = "0", message = "C"))
        }
        val service = MessageService(
            messageSource =  source,
            scope = backgroundScope,
        )

        // when
        val emittedMessages = service.observeMessage("0")
            .toListDuring(1.milliseconds)

        // then
        Assertions.assertEquals(
            listOf(
                Message(fromUserId = "0", message = "A"),
                Message(fromUserId = "0", message = "C")
            ), emittedMessages
        )
    }
}
