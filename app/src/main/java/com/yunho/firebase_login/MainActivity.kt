package com.yunho.firebase_login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.yunho.firebase_login.databinding.ActivityMainBinding
import com.yunho.firebase_login.main.Contract
import com.yunho.firebase_login.main.Presenter

class MainActivity : AppCompatActivity(), Contract.View {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private val presenter: Contract.Presenter by lazy {
        Presenter(this, auth)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        auth = Firebase.auth

        binding.btnLogOut.setOnClickListener(listener)
        binding.btnJoin.setOnClickListener(listener)
        binding.btnLogin.setOnClickListener(listener)
    }

    override fun onResume() {
        super.onResume()
        setView()
    }

    private val listener = View.OnClickListener {
        when(it.id) {
            R.id.btn_join -> {
                Intent(this, SignUpActivity::class.java).apply {
                    startActivity(this)
                }
            }
            R.id.btn_login -> signIn()
            R.id.btn_logOut -> {
                auth.signOut()
                setView()
            }
        }
    }

    override fun setView() {
        if (auth.currentUser == null) {
            binding.isSignIn = false
            binding.edtUserId.setText("")
            binding.edtUserPw.setText("")
            return
        }

        binding.isSignIn = true
        binding.connectedUser.text = "로그인 : ${auth.currentUser?.email ?: ""}"
    }

    override fun showAlertDialog(msg: String) {
        AlertDialog.Builder(this)
            .setMessage(msg)
            .setPositiveButton("확인") { _, _ -> }
            .show()
    }

    private fun signIn() {
        val id = binding.edtUserId.text.toString()
        if (!Utils.isValidEmail(id)) {
            showAlertDialog("올바른 이메일 형식이 아닙니다.")
            return
        }
        val pw = binding.edtUserPw.text.toString()
        Utils.sendFirebaseEvent(this, "로그인 클릭")
        presenter.signInUser(id, pw)
    }
}