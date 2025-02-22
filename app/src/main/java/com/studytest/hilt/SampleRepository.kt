package com.studytest.hilt

import javax.inject.Inject

class SampleRepository @Inject constructor(
    private val service: SampleService,
)
