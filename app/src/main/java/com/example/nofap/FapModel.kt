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
import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nofap.userRepository.userRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class FapModel(private val dataStore: userRepository) : ViewModel() {
    private val _uistate=dataStore.streakFlow.stateIn(viewModelScope, SharingStarted.WhileSubscribed(),0)
    val uistate:StateFlow<Long> = _uistate


    fun relapsed()
    {
        viewModelScope.launch{
                dataStore.savestreak(0)
        }
    }
    fun checkStreak(days: Long): Int {
        if(days>6 && days<13)return R.drawable.sigma
        if(days>13)return R.drawable.batman
    else return R.drawable.clown}
    fun increaseStreak(selectedDate: Long?)
    { if(selectedDate==null)return
        viewModelScope.launch{
            dataStore.savestartdate(selectedDate)
            val startDate = LocalDate.ofEpochDay(selectedDate / (24 * 60 * 60 * 1000))
            var current: LocalDate? = LocalDate.now()
            val dayssincestart= ChronoUnit.DAYS.between(startDate,current)
            dataStore.savestreak(dayssincestart)
        }


    }


}