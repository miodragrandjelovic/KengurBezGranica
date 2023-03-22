package com.example.kengur.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kengur.R
import com.example.kengur.dtos.request.LoginRequest
import com.example.kengur.dtos.response.LoginResponse
import com.example.kengur.utility.ActivityControl
import com.example.kengur.utility.ApiClient
import com.example.kengur.utility.Constants
import com.example.kengur.utility.SessionManager
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.ll_register
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    lateinit var apiClient: ApiClient
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        goToRegister()

        sessionManager = SessionManager(this)
        apiClient = ApiClient()

        ActivityControl.handleUserSignedIn(this,this,sessionManager,savedInstanceState)
        login()


    }

    override fun onRestart() {
        super.onRestart();
        ActivityControl.handleUserSignedIn(this,this, sessionManager, null)
        //resetInputs()
    }


    private fun login() {

        tv_login_l.setOnClickListener(){

            val loginRequest= LoginRequest(
                email = et_email_l.text.toString(),
                password = et_password_l.text.toString()
            )

            var context:Context = this
            apiClient.getUserService(context).login(loginRequest).enqueue(object : Callback<LoginResponse>{

                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {

                    if(response.isSuccessful)
                    {
                        sessionManager.saveToken(response.body()?.token.toString())
                        val intent = Intent(context, HomeActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                        finishAffinity()
                        startActivity(intent)
                    }
                    else if(response.code() == Constants.CODE_NOT_FOUND)
                        Toast.makeText(context, "Korisnik sa unetim email-om ne postoji.", Toast.LENGTH_SHORT).show()
                    else if(response.code() == Constants.CODE_BAD_REQUEST)
                        Toast.makeText(context, "Pogresna lozika!", Toast.LENGTH_SHORT).show()

                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(context, "Prijava neuspesna. Pokusaj ponovo.", Toast.LENGTH_LONG).show()
                }

            })

        }
    }

    private fun goToRegister() {
        ll_register_l.setOnClickListener(){
            val intent = Intent (this, RegisterActivity::class.java);
            startActivity(intent);
        }
    }
}