package com.studytest.kotlin.flow.test

import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.retry

// Sealed Class 정의
sealed class Appointment {
    data class AppointmentsUpdate(val appointments: List<Appointment>) : Appointment()
    data object AppointmentsAdd : Appointment()
    data object AppointmentsDelete : Appointment()
}

// Repository 인터페이스
interface AppointmentRepository {
    fun observeAppointments(): Flow<Appointment>
}

class ObserveAppointmentsService(
    private val appointmentRepository: AppointmentRepository,
) {
    // 원소 필터링, 매핑, 반복 원소 제거, 특정 예외의 경우 재시도 연산 수행
    fun observeAppointments(): Flow<List<Appointment>> =
        appointmentRepository
            .observeAppointments()
            .filterIsInstance<Appointment.AppointmentsUpdate>()
            .map { it.appointments }
            .distinctUntilChanged()
            .retry {
                it is ApiException && it.statusCode == CommonStatusCodes.NETWORK_ERROR
            }
}
