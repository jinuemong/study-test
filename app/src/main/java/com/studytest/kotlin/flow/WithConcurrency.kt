package com.studytest.kotlin.flow

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.toList

interface SampleApi {
    suspend fun requestOffers(name: String): List<String>
}

// Offer의 크기가 다를 때,
// 많은 요청을 처리할 경우 서버와 서비스에 좋지 않은 영향을 끼칩니다.
suspend fun getOffers(
    api : SampleApi,
    sellers: List<String>
): List<String> = coroutineScope {
    sellers
        .map { seller ->
            async { api.requestOffers(seller) }
        }
        .flatMap { it.await() }
}

// 동시성 속성이 적용 된 플로우 처리를 통해 이를 해결할 수 있습니다.
suspend fun getOffersWithFlow(
    api: SampleApi,
    sellers: List<String>,
): Flow<List<String>> = sellers
    .asFlow()
    .flatMapMerge(concurrency = 20) { seller ->
        suspend { api.requestOffers(seller) }.asFlow()
    }
// 동시 처리, 컨텍스트, 예외를 상황에 따라 조절할 수 있습니다.
