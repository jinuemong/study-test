package com.android.studytest.kotlin.flow.test

import com.studytest.kotlin.flow.test.Appointment
import com.studytest.kotlin.flow.test.AppointmentRepository
import kotlinx.coroutines.flow.Flow

// Fake 객체를 통한 테스트
class FakeAppointmentRepository(
    private val flow: Flow<Appointment>
): AppointmentRepository {
    override fun observeAppointments(): Flow<Appointment> = flow

}
