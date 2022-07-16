package com.yunho.firebase_login.signup

import com.google.firebase.auth.FirebaseAuth

class SignUpPresenter(
    private val view: SignUpContract.View,
    private val auth: FirebaseAuth
) : SignUpContract.Presenter {

    override fun createUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    view.createUserSuccess()
                }
            }
            .addOnFailureListener {
                it.printStackTrace()
                view.showAlertDialog("회원 가입에 실패했습니다.\n잠시 후 다시 시도하세요.")
            }
    }
}