package nackun.sample.phone.presentation.main

import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun MainApp(mainViewModel: MainViewModel) {
    Surface(color = MaterialTheme.colors.background) {
        Greeting("Android", mainViewModel::onButtonClick)
    }
}

@Composable
fun MainApp(mainDummyViewModel: MainDummyViewModel) {
    Surface(color = MaterialTheme.colors.background) {
        Greeting("Android") {}
    }
}

@Composable
fun Greeting(name: String, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = "Hello $name!")
    }
}
