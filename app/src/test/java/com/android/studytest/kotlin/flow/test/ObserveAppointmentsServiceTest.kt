package com.android.studytest.kotlin.flow.test

import com.android.studytest.utils.CoroutinesTestExtension
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status
import com.studytest.kotlin.flow.practice.flowOf
import com.studytest.kotlin.flow.test.Appointment
import com.studytest.kotlin.flow.test.ObserveAppointmentsService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(
    CoroutinesTestExtension::class
)
class ObserveAppointmentsServiceTest {
    private val anAppointment1 = Appointment.AppointmentsAdd
    private val anAppointment2 = Appointment.AppointmentsUpdate(
        appointments = listOf(anAppointment1)
    )

    // 리스트로 변환하여 테스트하기
    // 시간 의존성 테스트가 불가능하지만 직관적
    @Test
    fun `should keep only appointments from`() = runTest {
        //given
        val repo = FakeAppointmentRepository(
            flow = flowOf(
                anAppointment1,
                anAppointment1,
                anAppointment2,
                anAppointment2,
            )
        )
        val service = ObserveAppointmentsService(repo)

        // when
        val result = service.observeAppointments().toList()

        // then
        Assertions.assertEquals(listOf(listOf(anAppointment1)), result)
    }

    // 시간 의존성 테스트
    // 변화된 플로우에서 언제 원소가 방출되었는지에 대한 정보를 저장하여, 도착 시간 의존성을 확인할 수 있다.
    @Test
    fun `should eliminate elements that are`() = runTest {
        // given
        val repo = FakeAppointmentRepository(
            flow = flow {
                delay(1000)
                emit(Appointment.AppointmentsUpdate(listOf(anAppointment1)))
                emit(Appointment.AppointmentsUpdate(listOf(anAppointment1)))
                delay(1000)
                emit(Appointment.AppointmentsUpdate(listOf(anAppointment2)))
                delay(1000)
                emit(Appointment.AppointmentsUpdate(listOf(anAppointment2)))
                emit(Appointment.AppointmentsUpdate(listOf(anAppointment1)))
            }
        )
        val service = ObserveAppointmentsService(repo)

        // when
        val result = service.observeAppointments()
            .map { currentTime to it } // 도착 시간 확인
            .toList()
        //then
        Assertions.assertEquals(
            listOf(
                1000L to listOf(anAppointment1),
                2000L to listOf(anAppointment2),
                3000L to listOf(anAppointment1),
            ), result
        )
    }

    // 에러 코드 API 테스트하기
    // 재시도하지 않는 플로우를 반화할 경우 재시도 기능을 테스트할 필요가 없다.
    // 재시도하는 플로우를 반환하는 경우, 무한정 재시도 되어 플로우가 끝나지 않음
    // 원소 수를 제한하는 방법을 활용
    @Test
    fun `should retry when API exception`() = runTest {
        // given
        val status = Status(CommonStatusCodes.NETWORK_ERROR) // 예: NETWORK_ERROR 상태 코드
        val apiException = ApiException(status)
        val repo = FakeAppointmentRepository(
            flow {
                emit(Appointment.AppointmentsUpdate(listOf(anAppointment1)))
                throw apiException
            }
        )
        val service = ObserveAppointmentsService(repo)

        // when
        val result = service.observeAppointments()
            .take(3)
            .toList()

        // then
        Assertions.assertEquals(
            listOf(
                listOf(anAppointment1),
                listOf(anAppointment1),
                listOf(anAppointment1)
            ), result
        )
    }

}
