package com.example.kengur.activities

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Window
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
import com.example.kengur.utility.ApiClient
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.dialog_school_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {


    lateinit var apiClient: ApiClient
    private var schoolList: java.util.ArrayList<String> = java.util.ArrayList<String>()
    private var classList = arrayOf(
        "1. razred","2. razred","3. razred","4. razred","5. razred","6. razred","7. razred","8. razred","9. razred","10. razred","11. razred","12. razred"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        apiClient = ApiClient()
        setClass()
        searchSchool()
        register()


    }

    private fun register() {

        ib_register.setOnClickListener(){

            val ime = et_ime.text.toString()
            val prezime = et_prezime.text.toString()
            val email = et_email.text.toString()
            val skola = tv_school.text.toString()
            val razred_txt = tv_class.text.toString()
            val mesto = et_mesto.text.toString()
            val sifra = et_sifra.text.toString()
            var razred:Short=0

            if(ime!="" && prezime!="" && email!="" && skola!="Školaa" && razred_txt!="Razred" && mesto!="" && sifra!="")
            {

                for (i in classList.indices) {
                    if(classList[i]==razred_txt){
                        razred = i.toShort()
                        break
                    }
                }

                var registerDTO = RegisterRequest(
                    firstName = ime,
                    lastName = prezime,
                    email = email,
                    school = skola,
                    userClass = ++razred,
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

    private fun setClass() {

        fl_class.setOnClickListener(){

            val dialog = Dialog(this)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(true)
            dialog.setContentView(R.layout.dialog_class)
            dialog.window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())

            var listView: ListView = dialog.findViewById(R.id.list_view_classes)
            var classListAdapter: ArrayAdapter<String> = ArrayAdapter<String>(dialog.context, R.layout.item_search_school, classList)
            listView.adapter = classListAdapter

            dialog.show()

            listView.setOnItemClickListener { parent, view, position, id ->
                tv_class.text = classListAdapter.getItem(position).toString()
                dialog.dismiss()
            }
        }
    }

    private fun searchSchool() {

        fl_school.setOnClickListener(){

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