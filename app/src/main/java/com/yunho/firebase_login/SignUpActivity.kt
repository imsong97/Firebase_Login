package com.yunho.firebase_login

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.yunho.firebase_login.databinding.ActivitySignUpBinding
import com.yunho.firebase_login.signup.SignUpContract
import com.yunho.firebase_login.signup.SignUpPresenter

class SignUpActivity : AppCompatActivity(), SignUpContract.View {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth
    private val presenter: SignUpContract.Presenter by lazy {
        SignUpPresenter(this, auth)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        auth = Firebase.auth

        binding.btnJoin.setOnClickListener {
            createUser()
        }
    }

    private fun createUser() {
        val id = binding.userId.text.toString()
        if (!Utils.isValidEmail(id)) {
            showAlertDialog("올바른 이메일 형식이 아닙니다.")
            return
        }

        val pw = binding.userPw.text.toString()
        if (pw.length < 6) {
            showAlertDialog("비밀번호는 6자리 이상입니다.")
            return
        }
        Utils.sendFirebaseEvent(this, "회원 가입")
        presenter.createUser(id, pw)
    }

    override fun showAlertDialog(msg: String) {
        AlertDialog.Builder(this)
            .setMessage(msg)
            .setPositiveButton("확인") { _, _ -> }
            .show()
    }

    override fun createUserSuccess() {
        AlertDialog.Builder(this)
            .setMessage("가입이 완료되었습니다.")
            .setPositiveButton("확인") { _, _ -> finish() }
            .show()
    }
}