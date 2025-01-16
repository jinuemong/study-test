package com.studytest.kotlin.flow.test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

// 플로우 테스트를 뷰 모델에서 진행
// SharedFlow를 소스로 사용하고, 테스트에서 원소를 방출하는 방법
class ChatViewModel(
    private val scope : CoroutineScope,
    private val messageService: MessageService,
): ViewModel() {
    private val _lastMessage =
        MutableStateFlow<String?>(null)
    val lastMessage:StateFlow<String?> = _lastMessage

    private val _message =
        MutableStateFlow(emptyList<String>())
    val message:StateFlow<List<String>> = _message

    fun start(fromUserId: String){
        messageService.observeMessage(fromUserId)
            .onEach {
                val text = it.message
                _lastMessage.value = text
                _message.value += text
            }
            .launchIn(scope)
    }
}
