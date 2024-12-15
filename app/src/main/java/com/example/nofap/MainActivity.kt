package com.example.nofap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.nofap.ui.theme.NoFapTheme
import com.example.nofap.userRepository.MyApplication

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         //val fapmodel: FapModel by viewModels()
         lateinit var viewModel: FapModel
        val dataStore = (application as MyApplication).dataStore
        viewModel = FapModel(dataStore)
        enableEdgeToEdge()
        setContent {
            NoFapTheme {
                HomeScreen(viewModel)

            }
        }
    }
}

