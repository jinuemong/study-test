package com.studytest.hilt

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class SampleModule {
    @Binds
    abstract fun bindSampleService(
        sampleServiceImpl: SampleServiceImpl
    ): SampleService
}
