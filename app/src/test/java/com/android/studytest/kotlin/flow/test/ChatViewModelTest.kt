package com.android.studytest.kotlin.flow.test

import com.android.studytest.utils.CoroutinesTestExtension
import com.studytest.kotlin.flow.test.ChatViewModel
import com.studytest.kotlin.flow.test.Message
import com.studytest.kotlin.flow.test.MessageService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(
    CoroutinesTestExtension::class
)
class ChatViewModelTest {
    @Test
    fun `should expose message from user`() = runTest {
        //given
        val source = MutableSharedFlow<Message>(replay = 1)

        // when
        val viewModel = ChatViewModel(
            messageService = MessageService(
                messageSource = source,
                scope = backgroundScope,
            ),
            scope = backgroundScope
        )
        viewModel.start("0")
        // then
        Assertions.assertEquals(
            null,
            viewModel.lastMessage.value
        )

        // when
        source.emit(Message(fromUserId = "0", message = "ABC"))
        advanceTimeBy(1)
        // then
        Assertions.assertEquals(
            "ABC",
            viewModel.lastMessage.value
        )
        Assertions.assertEquals(
            listOf("ABC"),
            viewModel.message.value
        )

        // when
        source.emit(Message(fromUserId = "0", message = "DEF"))
        source.emit(Message(fromUserId = "1", message = "GHI"))
        advanceTimeBy(1)
        // then
        Assertions.assertEquals(
            "DEF",
            viewModel.lastMessage.value
        )
        Assertions.assertEquals(
            listOf("ABC", "DEF"),
            viewModel.message.value
        )
    }

}
