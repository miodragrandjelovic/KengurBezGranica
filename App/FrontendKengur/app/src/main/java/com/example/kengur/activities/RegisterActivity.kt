package com.example.kengur.activities

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toDrawable
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.example.kengur.R
import com.example.kengur.dtos.request.RegisterRequest
import com.example.kengur.dtos.response.MessageResponse
import com.example.kengur.models.School
import com.example.kengur.utility.ActivityControl
import com.example.kengur.utility.ApiClient
import com.example.kengur.utility.SessionManager
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.dialog_school_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {


    lateinit var apiClient: ApiClient
    lateinit var sessionManager:SessionManager
    private var schoolList: java.util.ArrayList<String> = java.util.ArrayList<String>()

    private var selectedClass:Short=1
    private var selectedGender:Short=0
    private var selectedGrade:Short=1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        apiClient = ApiClient()
        sessionManager = SessionManager(this)
        ActivityControl.handleUserSignedIn(this,this,sessionManager,savedInstanceState)


        spinnerClassInit()
        spinnerGradeInit()
        spinnerGenderInit()

        goToLogin()
        searchSchool()
        register()


    }

    private fun goToLogin() {
        ll_login.setOnClickListener(){
            val intent = Intent (this, LoginActivity::class.java);
            startActivity(intent);
        }
    }

    override fun onRestart() {
        super.onRestart();
        ActivityControl.handleUserSignedIn(this,this, sessionManager, null)
        //resetInputs()
    }


    private fun spinnerClassInit() {
        val razredArray = resources.getStringArray(R.array.razred_registracija_array)

        val adapter = ArrayAdapter(this, R.layout.registration_spinner_item, razredArray)

        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        spinnerRazred.adapter = adapter

        spinnerRazred.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: android.view.View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> selectedClass = 1
                    1 -> selectedClass = 2
                    2 -> selectedClass = 3
                    3 -> selectedClass = 4
                    4 -> selectedClass = 5
                    5 -> selectedClass = 6
                    6 -> selectedClass = 7
                    7 -> selectedClass = 8
                    8 -> selectedClass = 9
                    9 -> selectedClass = 10
                    10 -> selectedClass = 11
                    11 -> selectedClass = 12
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }
    private fun spinnerGenderInit() {
        val genderArray = resources.getStringArray(R.array.pol_array)

        val adapter = ArrayAdapter(this, R.layout.registration_spinner_item, genderArray)

        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        spinnerPol.adapter = adapter

        spinnerPol.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: android.view.View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> selectedGender = 0
                    1 -> selectedGender = 1
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }

    private fun spinnerGradeInit() {
        val gradeArray = resources.getStringArray(R.array.ocena_array)

        val adapter = ArrayAdapter(this, R.layout.registration_spinner_item, gradeArray)

        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        spinnerOcena.adapter = adapter

        spinnerPol.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: android.view.View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> selectedGrade = 1
                    1 -> selectedGrade = 2
                    2 -> selectedGrade = 3
                    3 -> selectedGrade = 4
                    4 -> selectedGrade = 5

                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }


    }



    private fun register() {

        tv_register.setOnClickListener(){

            val ime = et_ime.text.toString()
            val prezime = et_prezime.text.toString()
            val email = et_email.text.toString()
            val skola = tv_school.text.toString()
            val razred = selectedClass
            val mesto = et_mesto.text.toString()
            val sifra = et_sifra.text.toString()

            if(ime!="" && prezime!="" && email!="" && skola!="Škola"  && mesto!="" && sifra!="")
            {

                var registerDTO = RegisterRequest(
                    firstName = ime,
                    lastName = prezime,
                    email = email,
                    school = skola,
                    userClass = razred,
                    password = sifra
                )

                var context:Context= this

                apiClient.getUserService(context).register(registerDTO).enqueue(object : Callback<MessageResponse>{
                    override fun onResponse(
                        call: Call<MessageResponse>,
                        response: Response<MessageResponse>
                    ) {
                        if(response.isSuccessful){
                            val intent = Intent (context, LoginActivity::class.java);
                            startActivity(intent);
                            finish()
                        }
                    }

                    override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
                        Toast.makeText(context, "Registracija neuspesna. Pokusaj ponovo.", Toast.LENGTH_LONG).show()
                    }

                })

            }
            else
                Toast.makeText(this, "Moras popuniti sva polja!", Toast.LENGTH_LONG).show()

        }


    }

    private fun searchSchool() {

        tv_school.setOnClickListener(){

            val dialog = Dialog(this)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(true)
            dialog.setContentView(R.layout.dialog_school_search)
            dialog.window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())

            var listView: ListView = dialog.findViewById(R.id.list_view)
            var schoolListAdapter: ArrayAdapter<String> = ArrayAdapter<String>(dialog.context, R.layout.item_search_school, schoolList)
            listView.adapter = schoolListAdapter

            dialog.show()

            dialog.iv_searchIcon.setOnClickListener(){

                schoolListAdapter.clear()

                var context:Context = this

                var search = dialog.et_school_search.text.toString()
                if(search!="" && search.length>5)
                {
                    apiClient.getUserService(context).searchSchools(search).enqueue(object : Callback<ArrayList<School>>{
                        override fun onResponse(
                            call: Call<ArrayList<School>>,
                            response: Response<ArrayList<School>>
                        ) {
                            if(response.isSuccessful){

                                if(response.body()!!.isNotEmpty()){

                                    for(school in response.body()!!){
                                        var name = school.name+","+school.city
                                        schoolListAdapter.add(name)
                                    }
                                    schoolListAdapter.notifyDataSetChanged()
                                }

                            }
                        }

                        override fun onFailure(call: Call<ArrayList<School>>, t: Throwable) {
                            Toast.makeText(context, "Pokusajte ponovo.", Toast.LENGTH_LONG).show()
                        }

                    })

                }
                else
                    Toast.makeText(context, "Upisi puno ime skole!", Toast.LENGTH_LONG).show()

            }

            listView.setOnItemClickListener { parent, view, position, id ->
                tv_school.text = schoolListAdapter.getItem(position).toString()
                dialog.dismiss()
            }

        }

    }
}