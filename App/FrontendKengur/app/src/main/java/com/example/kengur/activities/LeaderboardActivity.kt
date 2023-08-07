package com.example.kengur.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.kengur.R
import com.example.kengur.adapters.LeaderboardsAdapter
import com.example.kengur.dtos.request.ResultRequest
import com.example.kengur.dtos.response.LeaderboardResponse
import com.example.kengur.utility.ActivityTransferStorage
import com.example.kengur.utility.ApiClient
import com.example.kengur.utility.SessionManager
import kotlinx.android.synthetic.main.activity_leaderboard.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.AdapterView
import android.widget.ArrayAdapter

class LeaderboardActivity : AppCompatActivity() {

    private lateinit var apiClient: ApiClient
    private lateinit var sessionManager: SessionManager
    private lateinit var userResult:ResultRequest
    private lateinit var leaderboardsAdapter: LeaderboardsAdapter
    private var position:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)

        apiClient = ApiClient()

        userResult = ActivityTransferStorage.leaderboardScore

        spinnerInit()

   //     leaderboardInit(userResult.userClass)

    }

    private fun spinnerInit() {
        val razredArray = resources.getStringArray(R.array.razred_array)

        // Create an ArrayAdapter using the string array and a default spinner layout
        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, razredArray)

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        // Apply the adapter to the spinner
        spinnerRazred.adapter = adapter

        val context = this
        // Set a listener for the spinner item selection
        spinnerRazred.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: android.view.View?,
                position: Int,
                id: Long
            ) {
                var selectedItem="1-2"
                when (position) {
                    0 -> selectedItem = "1-2"
                    1 -> selectedItem = "3-4"
                    2 -> selectedItem = "5-6"
                    3 -> selectedItem = "7-8"
                    4 -> selectedItem = "9-10"
                    5 -> selectedItem = "11-12"
                }
                leaderboardInit(selectedItem)


            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }


        var defaultSelectedPosition=0
        when (userResult.userClass) {
            "1-2" -> defaultSelectedPosition = 0
            "3-4" -> defaultSelectedPosition = 1
            "5-6" -> defaultSelectedPosition = 2
            "7-8" -> defaultSelectedPosition = 3
            "9-10" -> defaultSelectedPosition = 4
            "11-12" -> defaultSelectedPosition = 5
        }
        spinnerRazred.setSelection(defaultSelectedPosition)

    }

    private fun leaderboardInit(userClass:String) {

        var context= this
        apiClient.getLeaderboardService(context).getLeaderboard(userClass).enqueue(object : Callback<ArrayList<LeaderboardResponse>>{
            override fun onResponse(
                call: Call<ArrayList<LeaderboardResponse>>,
                response: Response<ArrayList<LeaderboardResponse>>
            ) {
                if(response.isSuccessful)
                {
                    var leaderboard = response.body()!!

                    if(leaderboard.size>0)
                    {
                        val fullName = leaderboard[0].userDTO.firstName+" "+leaderboard[0].userDTO.lastName
                        tv_fullname_1.text=fullName
                        tv_score_1.text=leaderboard[0].points.toString()
                        leaderboard.removeAt(0)
                    }
                    else
                    {
                        tv_fullname_1.text=""
                        tv_score_1.text=""
                    }
                    if(leaderboard.size>0)
                    {
                        val fullName = leaderboard[1].userDTO.firstName+" "+leaderboard[1].userDTO.lastName
                        tv_fullname_2.text=fullName
                        tv_score_2.text=leaderboard[1].points.toString()
                        leaderboard.removeAt(0)
                    }
                    else
                    {
                        tv_fullname_2.text=""
                        tv_score_2.text=""
                    }
                    if(leaderboard.size>0)
                    {
                        val fullName = leaderboard[2].userDTO.firstName+" "+leaderboard[2].userDTO.lastName
                        tv_fullname_3.text=fullName
                        tv_score_3.text=leaderboard[2].points.toString()
                        leaderboard.removeAt(0)
                    }
                    else
                    {
                        tv_fullname_3.text=""
                        tv_score_3.text=""
                    }

                    setLeaderboardRV()
                    leaderboardsAdapter.setPostList(leaderboard)


                }

            }

            override fun onFailure(call: Call<ArrayList<LeaderboardResponse>>, t: Throwable) {
                Toast.makeText(context, "Nesto nije u redu!", Toast.LENGTH_LONG).show()
            }

        })

    }


    private fun setLeaderboardRV() {
        leaderboardsAdapter = LeaderboardsAdapter(mutableListOf())
        rv_leaderboard.adapter = leaderboardsAdapter
        rv_leaderboard.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        (rv_leaderboard.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
    }


}