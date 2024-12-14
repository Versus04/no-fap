package com.example.nofap

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FapModel : ViewModel() {
    private val _uistate= MutableStateFlow(0)
    val uistate:StateFlow<Int> = _uistate.asStateFlow()


    fun relapsed()
    {
        _uistate.value= 0
        Log.d("relapsed", uistate.value.toString())
    }
    fun increaseStreak()
    {
        _uistate.value++
        Log.d("kya ho raha hai " , _uistate.value.toString())
    }

}