package com.nackun.wearableappsample

import android.content.Intent
import android.util.Log
import com.google.android.gms.wearable.DataEventBuffer
import com.google.android.gms.wearable.DataMapItem
import com.google.android.gms.wearable.MessageEvent
import com.google.android.gms.wearable.WearableListenerService

class DataLayerListenerService : WearableListenerService() {

    override fun onMessageReceived(messageEvent: MessageEvent) {
        super.onMessageReceived(messageEvent)

        when (messageEvent.path) {
            START_ACTIVITY_PATH -> {
                startActivity(
                    Intent(this, MainActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                )
            }
        }
    }

    override fun onDataChanged(dataEvents: DataEventBuffer) {
        super.onDataChanged(dataEvents)

        dataEvents.forEach { dataEvent ->
            val uri = dataEvent.dataItem.uri
            when (uri.path) {
                COUNT_PATH -> {
                    log(dataEvent.toString())
                    DataMapItem.fromDataItem(dataEvent.dataItem)
                        .dataMap.run {
                            getDouble(TIME_KEY)
                                .also {
                                    log("TIME_KEY getLong : $it")
                                }
                            getDouble(TIME_KEY_2)
                                .also {
                                    log("TIME_KEY_2 getLong : $it")
                                }
                        }
                }
            }
        }
    }

    private fun log(logMessage: String) = Log.d(TAG, logMessage)

    companion object {
        private const val TAG = "DataLayerService"

        private const val START_ACTIVITY_PATH = "/start-activity"

        private const val COUNT_PATH = "/count"
        private const val TIME_KEY = "time"
        private const val TIME_KEY_2 = "time2"
    }
}