package com.example.kengur.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.indices
import androidx.core.view.isGone
import com.example.kengur.R
import com.example.kengur.dtos.response.TaskResponse
import com.example.kengur.utility.ApiClient
import com.example.kengur.utility.UtilityFunctions
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_test.*
import kotlinx.android.synthetic.main.activity_test.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TestActivity : AppCompatActivity() {

    private lateinit var apiClient: ApiClient
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

        generateTest()
        previousTask()
        nextTask()
        addAnswer()

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
                Toast.makeText(this,"Nema prethodnog pitanja!",Toast.LENGTH_SHORT)
            }
            else
            {
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

            if(index==testTasks.size-1){
                Toast.makeText(this,"Nema sledećeg pitanja!",Toast.LENGTH_SHORT)
            }
            else
            {
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
    }

    private fun generateTest() {

        var context: Context = this
        var number = Class.toInt()
        if(number>=3 && number%2==1)
            Class=Class+"-"+(number+1).toString()

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
                Toast.makeText(context,"Nesto nije u redu!",Toast.LENGTH_LONG)
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