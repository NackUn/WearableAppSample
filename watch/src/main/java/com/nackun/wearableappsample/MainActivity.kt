package com.nackun.wearableappsample

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.nackun.wearableappsample.theme.WearableAppSampleTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WearApp("Android")
        }
    }


    @Composable
    fun WearApp(greetingName: String) {

        WearableAppSampleTheme {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background),
                verticalArrangement = Arrangement.Center
            ) {
                Greeting(greetingName = greetingName)
            }
        }
    }

    @Composable
    fun Greeting(greetingName: String) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    startActivity(Intent(this, NextActivity::class.java))
                },
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.primary,
            text = greetingName
        )
    }

    @Preview
    @Composable
    fun DefaultPreview() {
        WearApp("Preview Android")
    }
}