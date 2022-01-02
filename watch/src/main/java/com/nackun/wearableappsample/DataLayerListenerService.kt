package com.nackun.wearableappsample

import android.content.Intent
import com.google.android.gms.wearable.MessageEvent
import com.google.android.gms.wearable.WearableListenerService
import timber.log.Timber

class DataLayerListenerService : WearableListenerService() {

    override fun onMessageReceived(messageEvent: MessageEvent) {
        super.onMessageReceived(messageEvent)
        Timber.d("onMessageReceived")

        when (messageEvent.path) {
            START_ACTIVITY_PATH -> {
                startActivity(
                    Intent(this, MainActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                )
            }
        }
    }

    companion object {
        private const val START_ACTIVITY_PATH = "/start-activity"
    }
}