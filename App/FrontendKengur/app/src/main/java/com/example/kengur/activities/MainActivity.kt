package com.example.kengur.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kengur.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        goToLogin()
        goToRegister()

    }

    private fun goToLogin(){
        btn_login.setOnClickListener(){
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