package com.studytest.hilt

import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

// AppCompatActivity와 같은 ComponentActivity를 확장하는 활동 지원
@AndroidEntryPoint
class ExampleActivity : AppCompatActivity(){
    @Inject lateinit var repository: SampleRepository

}
