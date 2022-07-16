package com.yunho.firebase_login.signup

interface SignUpContract {

    interface View {
        fun showAlertDialog(msg: String)
        fun createUserSuccess()
    }

    interface Presenter {
        fun createUser(email: String, password: String)
    }
}