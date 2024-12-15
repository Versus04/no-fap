package com.example.nofap

import android.util.Log
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.intl.Locale
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class FapModel : ViewModel() {
    private val _uistate= MutableStateFlow(0)
    val uistate:StateFlow<Int> = _uistate.asStateFlow()


    fun relapsed()
    {
        _uistate.value= 0
        Log.d("relapsed", uistate.value.toString())
    }
    fun increaseStreak(selectedDate: Long?)
    { if(selectedDate==null)return
        val startDate = LocalDate.ofEpochDay(selectedDate / (24 * 60 * 60 * 1000))
        var current: LocalDate? = LocalDate.now()
        val dayssincestart= ChronoUnit.DAYS.between(startDate,current)
        _uistate.value=dayssincestart.toInt()

    }


}