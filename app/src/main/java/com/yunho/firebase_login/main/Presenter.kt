package com.yunho.firebase_login.main

import com.google.firebase.auth.FirebaseAuth

class Presenter(
    private val view: Contract.View,
    private val auth: FirebaseAuth
) : Contract.Presenter {

    override fun signInUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    view.setView()
                }
            }
            .addOnFailureListener {
                it.printStackTrace()
                view.showAlertDialog("아이디 또는 비밀번호를 확인하세요.")
            }
    }
}