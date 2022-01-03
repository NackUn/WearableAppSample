package nackun.sample.phone.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.wearable.Wearable
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import nackun.sample.phone.presentation.ui.theme.WearableAppSampleTheme
import timber.log.Timber

class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    private val nodeClient by lazy { Wearable.getNodeClient(this) }
    private val messageClient by lazy { Wearable.getMessageClient(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initObserve()
        setContent {
            WearableAppSampleTheme {
                MainApp(mainViewModel)
            }
        }
    }

    private fun initObserve() {
        lifecycleScope.launch {
            mainViewModel.buttonClickEvent.collect {
                Timber.d("mainViewModel.buttonClickEvent.collect")
                startWearableActivity()
            }
        }
    }

    private fun startWearableActivity() {
        lifecycleScope.launch {
            try {
                val nodes = nodeClient.connectedNodes.await()

                // Send a message to all nodes in parallel
                nodes.map { node ->
                    async {
                        messageClient.sendMessage(node.id, START_ACTIVITY_PATH, byteArrayOf())
                            .await()
                    }
                }.awaitAll()

                Timber.d("Starting activity requests sent successfully")
            } catch (cancellationException: CancellationException) {
                throw cancellationException
            } catch (exception: Exception) {
                Timber.d("Starting activity failed: $exception")
            }
        }
    }

    companion object {
        private const val START_ACTIVITY_PATH = "/start-activity"
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WearableAppSampleTheme {
        MainApp(MainDummyViewModel())
    }
}
