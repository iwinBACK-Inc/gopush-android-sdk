package com.iwinback.notificationsample

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.gopush.GoPush

class SampleMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        Log.d("SampleMessagingService", "New message received: ${message.data}")
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        GoPush.setPushToken(this, token)
        Log.d("SampleMessagingService", "New token: ${token}")
    }
}
