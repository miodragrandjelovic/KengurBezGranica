package com.example.kengur.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kengur.R
import com.example.kengur.utility.ActivityControl
import com.example.kengur.utility.SessionManager
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.ll_register

class MainActivity : AppCompatActivity() {

    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sessionManager = SessionManager(this)
        ActivityControl.handleUserSignedIn(this,this,sessionManager,savedInstanceState)

        goToLogin()
        goToRegister()


        btn.setOnClickListener(){
            val intent = Intent (this, HomeActivity::class.java);
            startActivity(intent);
        }

    }

    override fun onRestart() {
        super.onRestart();
        ActivityControl.handleUserSignedIn(this, this, sessionManager, null)
    }

    private fun goToLogin(){
        tv_login_m.setOnClickListener(){
            val intent = Intent (this, LoginActivity::class.java);
            startActivity(intent);
        }

    }

    private fun goToRegister(){
        ll_register.setOnClickListener(){
            val intent = Intent (this, RegisterActivity::class.java);
            startActivity(intent);
        }

    }

}