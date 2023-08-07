package com.example.kengur.activities

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.core.view.isGone
import com.example.kengur.R
import com.example.kengur.dtos.request.StatisticRequest
import com.example.kengur.dtos.request.ResultRequest
import com.example.kengur.dtos.response.MessageResponse
import com.example.kengur.dtos.response.TaskResponse
import com.example.kengur.utility.ActivityTransferStorage
import com.example.kengur.utility.ApiClient
import com.example.kengur.utility.SessionManager
import com.example.kengur.utility.UtilityFunctions
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_test.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TestActivity : AppCompatActivity() {

    private lateinit var apiClient: ApiClient
    private lateinit var sessionManager: SessionManager
    private lateinit var Class:String
    private lateinit var testTasks : ArrayList<TaskResponse>
    private var index:Int = 0
    private var answers:ArrayList<Int> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val bundle = intent.extras
        Class = bundle?.getString("Class")!!

        apiClient = ApiClient()
        sessionManager = SessionManager(this)

        generateTest()
        previousTask()
        nextTask()
        addAnswer()
        finishTest()

    }

    private fun finishTest() {

        finish.setOnClickListener(){

            var points:Double= 0.0
            var statisticArray:ArrayList<StatisticRequest> = arrayListOf()

            for(i in answers.indices){

                if(answers[i]==testTasks[i].correctAnswerIndex)
                {
                    points+=testTasks[i].level
                    statisticArray.add(StatisticRequest(testTasks[i].id,true))
                }

                else
                {
                    points -= testTasks[i].level / 4
                    statisticArray.add(StatisticRequest(testTasks[i].id,false))
                }
            }

            var context: Context = this

            apiClient.getTestService(context).sendStatistic(statisticArray).enqueue(object : Callback<MessageResponse>{
                override fun onResponse(
                    call: Call<MessageResponse>,
                    response: Response<MessageResponse>
                ) {
                    if (response.isSuccessful)
                        Log.d("","Statistika testa uspesno poslata")
                    else
                        Toast.makeText(context, "Nesto nije u redu!", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
                    Toast.makeText(context, "Nesto nije u redu!", Toast.LENGTH_LONG).show()
                }

            })


            if (points<=0.0)
                points=0.0

            val dialog = Dialog(this)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.dialog_test_result)
            dialog.window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())

            dialog.show()

            var tvHomePage:TextView = dialog.findViewById(R.id.home_page)
            var dialogPoints:TextView = dialog.findViewById(R.id.dialog_points)
            var rankingList:TextView = dialog.findViewById(R.id.ranking_list)
            var message:TextView = dialog.findViewById(R.id.tv_message)


            dialogPoints.text=points.toString()+" poena!"


            //upisi rezultat u rang listu
            //mora da se zna: 1)broj poena 2)nivo testa
            rankingList.setOnClickListener() {

                //ukoliko je korisnik vec obavesten da rezultat ne moze da se upise u rang listu
                if (!message.isGone) {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    dialog.dismiss()
                }

                //u suprotnom proveri
                else {

                    var user = sessionManager.fetchUserData()

                    val resultRequest = ResultRequest(
                        email = user!!.email,
                        userClass = Class,
                        score = points
                    )

                    apiClient.getLeaderboardService(context).addResult(resultRequest)
                        .enqueue(object : Callback<MessageResponse> {
                            override fun onResponse(
                                call: Call<MessageResponse>,
                                response: Response<MessageResponse>
                            ) {
                                if (response.isSuccessful) {

                                    ActivityTransferStorage.leaderboardScore= resultRequest
                                    val intent = Intent(context, LeaderboardActivity::class.java)
                                    startActivity(intent)
                                    dialog.dismiss()
                                    finish()
                                } else {
                                    message.isGone = false
                                }

                            }

                            override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
                                Toast.makeText(context, "Nesto nije u redu!", Toast.LENGTH_LONG).show()
                            }

                        })


                }
            }

            //vrati se na pocetnu stranicu
            tvHomePage.setOnClickListener(){
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                dialog.dismiss()
                finish()
            }

        }

    }

    private fun addAnswer() {

        answer_1.setOnClickListener(){

            if(answers[index]!=-1 && answers[index]!=0){
                deleteSelected()
            }

            answers[index]=0
            fl_1.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_blue))
            tv_A.setTextColor(ContextCompat.getColor(this, R.color.white))
            tv_A.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_blue))
            mcv_A.strokeColor = ContextCompat.getColor(this, R.color.white)

        }

        answer_2.setOnClickListener(){

            if(answers[index]!=-1 && answers[index]!=1){
                deleteSelected()
            }

            answers[index]=1
            fl_2.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_blue))
            tv_B.setTextColor(ContextCompat.getColor(this, R.color.white))
            tv_B.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_blue))
            mcv_B.strokeColor = ContextCompat.getColor(this, R.color.white)

        }

        answer_3.setOnClickListener(){

            if(answers[index]!=-1 && answers[index]!=2){
                deleteSelected()
            }

            answers[index]=2
            fl_3.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_blue))
            tv_V.setTextColor(ContextCompat.getColor(this, R.color.white))
            tv_V.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_blue))
            mcv_V.strokeColor = ContextCompat.getColor(this, R.color.white)
        }

        answer_4.setOnClickListener(){

            if(answers[index]!=-1 && answers[index]!=3){
                deleteSelected()
            }

            answers[index]=3
            fl_4.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_blue))
            tv_G.setTextColor(ContextCompat.getColor(this, R.color.white))
            tv_G.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_blue))
            mcv_G.strokeColor = ContextCompat.getColor(this, R.color.white)
        }

        answer_5.setOnClickListener(){

            if(answers[index]!=-1 && answers[index]!=4){
                deleteSelected()
            }

            answers[index]=4
            fl_5.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_blue))
            tv_D.setTextColor(ContextCompat.getColor(this, R.color.white))
            tv_D.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_blue))
            mcv_D.strokeColor = ContextCompat.getColor(this, R.color.white)
        }

    }

    private fun deleteSelected() {

        if(answers[index]==0)
        {
            fl_1.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
            tv_A.setTextColor(ContextCompat.getColor(this, R.color.black))
            tv_A.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
            mcv_A.strokeColor = ContextCompat.getColor(this, R.color.black)
        }

        else if(answers[index]==1)
        {
            fl_2.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
            tv_B.setTextColor(ContextCompat.getColor(this, R.color.black))
            tv_B.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
            mcv_B.strokeColor = ContextCompat.getColor(this, R.color.black)
        }
        else if(answers[index]==2)
        {
            fl_3.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
            tv_V.setTextColor(ContextCompat.getColor(this, R.color.black))
            tv_V.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
            mcv_V.strokeColor = ContextCompat.getColor(this, R.color.black)
        }
        else if(answers[index]==3)
        {
            fl_4.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
            tv_G.setTextColor(ContextCompat.getColor(this, R.color.black))
            tv_G.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
            mcv_G.strokeColor = ContextCompat.getColor(this, R.color.black)
        }
        else
        {
            fl_5.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
            tv_D.setTextColor(ContextCompat.getColor(this, R.color.black))
            tv_D.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
            mcv_D.strokeColor = ContextCompat.getColor(this, R.color.black)
        }
    }

    private fun previousTask() {

        task_previous.setOnClickListener(){

            if(index==0){
                Toast.makeText(this,"Nema prethodnog pitanja!",Toast.LENGTH_SHORT).show()
            }
            else
            {
                if(index==testTasks.size-1){
                    finish.isGone=true
                    task_next.isGone=false
                }

                defaultAll()
                index--
                if(answers[index]!=-1)
                {
                   if(answers[index]==0)
                   {
                       fl_1.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_blue))
                       tv_A.setTextColor(ContextCompat.getColor(this, R.color.white))
                       tv_A.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_blue))
                       mcv_A.strokeColor = ContextCompat.getColor(this, R.color.white)
                   }
                   else if(answers[index]==1)
                   {
                       fl_2.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_blue))
                       tv_B.setTextColor(ContextCompat.getColor(this, R.color.white))
                       tv_B.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_blue))
                       mcv_B.strokeColor = ContextCompat.getColor(this, R.color.white)
                   }
                   else if(answers[index]==2)
                   {
                       fl_3.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_blue))
                       tv_V.setTextColor(ContextCompat.getColor(this, R.color.white))
                       tv_V.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_blue))
                       mcv_V.strokeColor = ContextCompat.getColor(this, R.color.white)
                   }
                   else if(answers[index]==3)
                   {
                       fl_4.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_blue))
                       tv_G.setTextColor(ContextCompat.getColor(this, R.color.white))
                       tv_G.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_blue))
                       mcv_G.strokeColor = ContextCompat.getColor(this, R.color.white)
                   }
                   else
                   {
                       fl_5.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_blue))
                       tv_D.setTextColor(ContextCompat.getColor(this, R.color.white))
                       tv_D.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_blue))
                       mcv_D.strokeColor = ContextCompat.getColor(this, R.color.white)
                   }


                }

                generateTask()
            }
        }

    }

    private fun defaultAll() {
        fl_1.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
        tv_A.setTextColor(ContextCompat.getColor(this, R.color.black))
        tv_A.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
        mcv_A.strokeColor = ContextCompat.getColor(this, R.color.black)

        fl_2.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
        tv_B.setTextColor(ContextCompat.getColor(this, R.color.black))
        tv_B.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
        mcv_B.strokeColor = ContextCompat.getColor(this, R.color.black)

        fl_3.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
        tv_V.setTextColor(ContextCompat.getColor(this, R.color.black))
        tv_V.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
        mcv_V.strokeColor = ContextCompat.getColor(this, R.color.black)

        fl_4.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
        tv_G.setTextColor(ContextCompat.getColor(this, R.color.black))
        tv_G.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
        mcv_G.strokeColor = ContextCompat.getColor(this, R.color.black)

        fl_5.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
        tv_D.setTextColor(ContextCompat.getColor(this, R.color.black))
        tv_D.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
        mcv_D.strokeColor = ContextCompat.getColor(this, R.color.black)
    }

    private fun nextTask() {

        task_next.setOnClickListener(){

                if(index==testTasks.size-2){
                    finish.isGone=false
                    task_next.isGone=true
                }

                defaultAll()
                index++
                if(answers[index]!=-1)
                {
                    if(answers[index]==0)
                    {
                        fl_1.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_blue))
                        tv_A.setTextColor(ContextCompat.getColor(this, R.color.white))
                        tv_A.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_blue))
                        mcv_A.strokeColor = ContextCompat.getColor(this, R.color.white)
                    }
                    else if(answers[index]==1)
                    {
                        fl_2.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_blue))
                        tv_B.setTextColor(ContextCompat.getColor(this, R.color.white))
                        tv_B.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_blue))
                        mcv_B.strokeColor = ContextCompat.getColor(this, R.color.white)
                    }
                    else if(answers[index]==2)
                    {
                        fl_3.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_blue))
                        tv_V.setTextColor(ContextCompat.getColor(this, R.color.white))
                        tv_V.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_blue))
                        mcv_V.strokeColor = ContextCompat.getColor(this, R.color.white)
                    }
                    else if(answers[index]==3)
                    {
                        fl_4.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_blue))
                        tv_G.setTextColor(ContextCompat.getColor(this, R.color.white))
                        tv_G.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_blue))
                        mcv_G.strokeColor = ContextCompat.getColor(this, R.color.white)
                    }
                    else
                    {
                        fl_5.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_blue))
                        tv_D.setTextColor(ContextCompat.getColor(this, R.color.white))
                        tv_D.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_blue))
                        mcv_D.strokeColor = ContextCompat.getColor(this, R.color.white)
                    }


                }
                generateTask()
        }
    }

    private fun generateTest() {

        var context: Context = this

        when (Class) {
            "0" -> Class = "1"
            "1" -> Class = "2"
            "2" -> Class = "3-4"
            "3" -> Class = "5-6"
            "4" -> Class = "7-8"
            "5" -> Class = "9-10"
            "6" -> Class = "11-12"
        }

        apiClient.getTestService(context).generateTest(Class).enqueue(object : Callback<ArrayList<TaskResponse>>{
            override fun onResponse(
                call: Call<ArrayList<TaskResponse>>,
                response: Response<ArrayList<TaskResponse>>
            ) {
                if(response.isSuccessful)
                {
                    testTasks=response.body()!!
                    generateTask()
                    for(i in testTasks.indices)
                        answers.add(-1)

                }
            }

            override fun onFailure(call: Call<ArrayList<TaskResponse>>, t: Throwable) {
                Toast.makeText(context,"Nesto nije u redu!",Toast.LENGTH_LONG).show()
            }

        })

    }

    private fun generateTask(){

        var task = testTasks[index]

        task_text.text = task.taskText
        if(task.taskPicture!="") {

            Picasso.get().load(UtilityFunctions.getFullImagePath(task.taskPicture))
                .into(task_picture)
            cv_task_picture.isGone = false
        }
        else
            cv_task_picture.isGone = true

        if(task.answersPictures.size==0)
        {
            answer_text_1.text=task.answersText[0]
            answer_text_2.text=task.answersText[1]
            answer_text_3.text=task.answersText[2]
            answer_text_4.text=task.answersText[3]
            answer_text_5.text=task.answersText[4]

            cv_answer_picture_1.isGone=true
            cv_answer_picture_2.isGone=true
            cv_answer_picture_3.isGone=true
            cv_answer_picture_4.isGone=true
            cv_answer_picture_5.isGone=true

            answer_text_1.isGone=false
            answer_text_2.isGone=false
            answer_text_3.isGone=false
            answer_text_4.isGone=false
            answer_text_5.isGone=false


        }
        else
        {
            Picasso.get().load(UtilityFunctions.getFullImagePath(task.answersPictures[0])).into(answer_picture_1)
            Picasso.get().load(UtilityFunctions.getFullImagePath(task.answersPictures[1])).into(answer_picture_2)
            Picasso.get().load(UtilityFunctions.getFullImagePath(task.answersPictures[2])).into(answer_picture_3)
            Picasso.get().load(UtilityFunctions.getFullImagePath(task.answersPictures[3])).into(answer_picture_4)
            Picasso.get().load(UtilityFunctions.getFullImagePath(task.answersPictures[4])).into(answer_picture_5)

            cv_answer_picture_1.isGone=false
            cv_answer_picture_2.isGone=false
            cv_answer_picture_3.isGone=false
            cv_answer_picture_4.isGone=false
            cv_answer_picture_5.isGone=false

            answer_text_1.isGone=true
            answer_text_2.isGone=true
            answer_text_3.isGone=true
            answer_text_4.isGone=true
            answer_text_5.isGone=true

        }
        task_number.text="Zadatak "+(index+1).toString()+"." // redni broj zadatka

        task_points.text = task.level.toString()+" poena"

        // deo za progress bar
        var barWidth:Int
        if(index!=0)
            barWidth = frameLayout.width/testTasks.size*(index+1)
        else
            barWidth = frameLayout.width/testTasks.size

        var layoutParams = FrameLayout.LayoutParams(barWidth, frameLayout.height)
        progress_bar.layoutParams = layoutParams

    }


}