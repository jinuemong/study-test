package com.studytest.hilt

import android.content.Context
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class SampleAdapter @Inject constructor(
    @ActivityContext private val context: Context
) {
}
