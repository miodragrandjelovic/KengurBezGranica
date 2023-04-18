package com.example.kengur.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

class LeaderboardActivity : AppCompatActivity() {

    private lateinit var apiClient: ApiClient
    private lateinit var sessionManager: SessionManager
    private lateinit var userResult:ResultRequest
    private lateinit var leaderboardsAdapter: LeaderboardsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)

        apiClient = ApiClient()

        userResult = ActivityTransferStorage.leaderboardScore



        leaderboardInit()



    }

    private fun leaderboardInit() {

        var context= this
        apiClient.getLeaderboardService(context).getLeaderboard(userResult.userClass).enqueue(object : Callback<ArrayList<LeaderboardResponse>>{
            override fun onResponse(
                call: Call<ArrayList<LeaderboardResponse>>,
                response: Response<ArrayList<LeaderboardResponse>>
            ) {
                if(response.isSuccessful)
                {
                    var leaderboard = response.body()!!
                    if(leaderboard[0]!=null)
                    {
                        tv_fullname_1.text=leaderboard[0].fullName
                        tv_score_1.text=leaderboard[0].score.toString()
                    }
                    if(leaderboard[1]!=null)
                    {
                        tv_fullname_2.text=leaderboard[1].fullName
                        tv_score_2.text=leaderboard[1].score.toString()
                    }
                    if(leaderboard[2]!=null)
                    {
                        tv_fullname_3.text=leaderboard[2].fullName
                        tv_score_3.text=leaderboard[2].score.toString()
                    }

                    leaderboard.removeAt(0)
                    leaderboard.removeAt(0)
                    leaderboard.removeAt(0)

                    setLeaderboardRV()
                    leaderboardsAdapter.setPostList(leaderboard)


                }

            }

            override fun onFailure(call: Call<ArrayList<LeaderboardResponse>>, t: Throwable) {
                Toast.makeText(context, "Nesto nije u redu!", Toast.LENGTH_LONG)
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