package com.yunho.firebase_login

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class PushMessageService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        println("data: ${message.data}")
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}