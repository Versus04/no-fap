package com.example.nofap.userRepository

import android.app.Application

class MyApplication: Application() {

    val dataStore by lazy { userRepository(this) }
}