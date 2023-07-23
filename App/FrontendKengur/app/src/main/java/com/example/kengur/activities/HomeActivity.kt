package com.example.kengur.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.PopupWindow
import android.widget.Toast
import com.example.kengur.R
import com.example.kengur.dtos.response.UserResponse
import com.example.kengur.utility.ApiClient
import com.example.kengur.utility.SessionManager
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.popup_menu.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.math.BigDecimal
import java.math.RoundingMode

class HomeActivity : AppCompatActivity() {

    lateinit var apiClient: ApiClient
    lateinit var sessionManager: SessionManager

    var flag=1

    lateinit var  window: PopupWindow
    lateinit var view : View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        view = layoutInflater.inflate(R.layout.popup_menu,null)
        window = PopupWindow(this)

        window.contentView = view

        popup()

        apiClient = ApiClient()
        sessionManager = SessionManager(this)

        profileInit()
        testStart()

    }

    override fun onRestart() {
        super.onRestart()

        profileInit()
    }

    override fun onResume() {
        super.onResume()

        profileInit()
    }

    private fun testStart() {
        component_test.setOnClickListener(){

            val intent = Intent(this, ChooseTestActivity::class.java)
            startActivity(intent)

        }
    }

    private fun popup() {

        menu_btn.setOnClickListener() {

            view.settings.setOnClickListener() {

              //  val intent = Intent(this, ChangeCredentialsActivity::class.java);
            //    ActivityTransferStorage.changeCredentialsInformation = changeCredentialsInformation
            //    startActivity(intent);
            }

            view.logout.setOnClickListener() {
                SessionManager(this).clearToken()
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }

            if (flag==-1) {
                window.dismiss()
            }
            else
            {
                window.showAsDropDown(menu_btn)
            }

            flag*=-1
        }
    }

    private fun profileInit() {

        var context:Context = this
        var user = sessionManager.fetchUserData()

        apiClient.getUserService(context).getUser(user!!.email).enqueue(object : Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {

                if(response.isSuccessful){

                    tv_firstname_h.text = response.body()!!.firstName
                    tv_lastname_h.text = response.body()!!.lastName
                    tv_schoolName_h.text = response.body()!!.school.name
                    tv_schoolCity_h.text = response.body()!!.school.city
                    tv_testnumber_h.text = response.body()!!.testNumber.toString()
                    tv_class_h.text = response.body()!!.userClass.toString()
                    if(response.body()!!.testNumber==0)
                        tv_average_h.text = "0"
                    else {
                        var avg = response.body()!!.sumPoints / response.body()!!.testNumber
                        val decimal = BigDecimal(avg.toDouble())
                        val roundedValue = decimal.setScale(2, RoundingMode.HALF_EVEN)
                        tv_average_h.text = roundedValue.toString()
                    }
                }

            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Toast.makeText(context, "Something went wrong.", Toast.LENGTH_LONG).show()
            }

        })


    }
}