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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.getValue

@Composable
fun HomeScreen(noFapModel: FapModel)
{
    val uistate by noFapModel.uistate.collectAsState()
    Surface(modifier = Modifier
        .statusBarsPadding()
        .fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            streak(uistate.toString())

          }
        Box(contentAlignment = Alignment.BottomEnd, modifier = Modifier.padding(32.dp)){
            clownbutton({noFapModel.relapsed()})
        }
    }

}


@Composable
fun streak(streakdays: String)
{
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Image(painter = painterResource(R.drawable.sigma), contentDescription = null)
        Text("You haven't fapped for $streakdays days!!!")


    }
}
@Composable
fun clownbutton( onclick: ()-> Unit)
{   var colors = ButtonColors(
    containerColor = MaterialTheme.colorScheme.onBackground, // or Color.Black
    contentColor = Color.White,
    disabledContainerColor = Color.Gray,
    disabledContentColor = Color.LightGray
)
    Button( onclick,modifier = Modifier
        .size(100.dp)
        .clip(CircleShape), shape = CircleShape, colors = colors) {Image(painter = painterResource(R.drawable.clown), contentDescription = null) }
}


