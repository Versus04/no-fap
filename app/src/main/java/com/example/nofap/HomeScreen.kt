package com.example.nofap

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(noFapModel: FapModel) {
    var selectedDate by remember { mutableStateOf<Long?>(null) }
    var showModalInput by remember { mutableStateOf(false) }

    val uiState by noFapModel.uistate.collectAsState()

    Surface(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Streak(streakDays = uiState.toString(),noFapModel)

            if (selectedDate != null) {
                val formattedDate = java.text.SimpleDateFormat("MMM dd, yyyy", java.util.Locale.getDefault())
                    .format(java.util.Date(selectedDate!!))

            }

            if (showModalInput) {
                DatePickerModalInput(
                    onDateSelected = {
                        selectedDate = it
                        showModalInput = false
                        noFapModel.increaseStreak(selectedDate) // Call relapsed after selecting date
                    },
                    onDismiss = { showModalInput = false }
                )
            }
        }

        Box(
            contentAlignment = Alignment.BottomEnd,
            modifier = Modifier.padding(32.dp)
        ) {
            ClownButton(onClick = { showModalInput = true})
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModalInput(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState(
        initialDisplayMode = DisplayMode.Input
    )

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

@Composable
fun Streak(streakDays: String , fapModel: FapModel) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(fapModel.checkStreak(streakDays.toLong())),
            contentDescription = "Streak Image"
        )
        Text("You haven't fapped for $streakDays days!!!")
    }
}

@Composable
fun ClownButton(onClick: () -> Unit) {
    val colors = ButtonColors(
        containerColor = MaterialTheme.colorScheme.onBackground,
        contentColor = Color.White,
        disabledContainerColor = Color.Gray,
        disabledContentColor = Color.LightGray
    )

    Button(
        onClick = onClick,
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape),
        shape = CircleShape,
        colors = colors
    ) {
        Image(
            painter = painterResource(R.drawable.clown),
            contentDescription = "Relapse Button"
        )
    }
}