package com.yunho.firebase_login

import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

object Utils {

    fun sendFirebaseEvent(context: Context, eventName: String) {
        val analytics = FirebaseAnalytics.getInstance(context)
        analytics.logEvent(eventName, Bundle())
    }

    fun isValidEmail(email: String): Boolean {
        val pattern = android.util.Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }
}