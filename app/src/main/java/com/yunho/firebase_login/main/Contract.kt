package com.yunho.firebase_login.main

import com.google.firebase.auth.FirebaseUser

interface Contract {

    interface View {
        fun setView()
        fun showAlertDialog(msg: String)
    }

    interface Presenter {
        fun signInUser(email: String, password: String)
    }
}