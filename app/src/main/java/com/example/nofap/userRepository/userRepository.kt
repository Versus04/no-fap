package com.example.nofap.userRepository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.Flow
const val PREFERENCE_NAME="my_preference"

class userRepository(private val context: Context) {
   companion object
   {
       private val Context.dataStore by preferencesDataStore(PREFERENCE_NAME)
       val longesstreak = longPreferencesKey("longestStreak")
       val startDate = longPreferencesKey("start_date")
       val firsttime = booleanPreferencesKey("first_time")
   }
    suspend fun savestreak(streak: Long)
    {
        context.dataStore.edit { preferences->
            preferences[longesstreak]=streak
        }
    }
    val streakFlow: Flow<Long> = context.dataStore.data
        .map { preferences ->
            preferences[longesstreak] ?: 0L
        }

    suspend fun savestartdate(startdate: Long)
    {
        context.dataStore.edit {
            preferences->
            preferences[startDate]=startdate
        }
    }
    val startDateFlow: Flow<Long> = context.dataStore.data
        .map { preferences ->
            preferences[startDate] ?: 0L
        }
    suspend fun first(firstLogin: Boolean)
    {
        context.dataStore.edit { preferences->
            preferences[firsttime]=firstLogin
        }
    }
    val loginFlow: Flow<Boolean> = context.dataStore.data.map { preferences->
        preferences[firsttime] ?:false
    }

}