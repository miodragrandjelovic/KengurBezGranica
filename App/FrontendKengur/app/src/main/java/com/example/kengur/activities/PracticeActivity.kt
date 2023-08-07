package com.example.kengur.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import com.example.kengur.R
import com.example.kengur.dtos.response.TaskResponse
import com.example.kengur.utility.ApiClient
import com.example.kengur.utility.SessionManager
import com.example.kengur.utility.UtilityFunctions
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_practice.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PracticeActivity : AppCompatActivity() {

    private lateinit var tasks : ArrayList<TaskResponse>
    private var index:Int = 0

    private lateinit var apiClient: ApiClient
    private lateinit var sessionManager: SessionManager

    private var selectedClass:String="9-10"
    private var selectedLevel:Int=3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice)

        apiClient = ApiClient()
        sessionManager = SessionManager(this)

        spinnerClassInit()
        spinnerLevelInit()

        guessTheAnswer()
        nextTask()

    }

    private fun spinnerClassInit() {
        val razredArray = resources.getStringArray(R.array.razred_array)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, razredArray)

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
                    0 -> selectedClass = "1-2"
                    1 -> selectedClass = "3-4"
                    2 -> selectedClass = "5-6"
                    3 -> selectedClass = "7-8"
                    4 -> selectedClass = "9-10"
                    5 -> selectedClass = "11-12"
                }
                getTasksFiltered()


            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        var defaultSelectedPosition=4

        spinnerRazred.setSelection(defaultSelectedPosition)
    }

    private fun spinnerLevelInit() {
        val nivoArray = resources.getStringArray(R.array.vrednost_zadatka_array)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, nivoArray)

        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        spinnerNivo.adapter = adapter

        spinnerNivo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: android.view.View?,
                position: Int,
                id: Long
            ) {
                when(position){
                    0 -> selectedLevel = 3
                    1 -> selectedLevel = 4
                    2 -> selectedLevel = 5
                }

                getTasksFiltered()

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }

    private fun getTasksFiltered() {

        var context:Context = this
        apiClient.getTestService(context).getTasksFiltered(selectedClass,selectedLevel).enqueue(object : Callback<ArrayList<TaskResponse>>{
            override fun onResponse(
                call: Call<ArrayList<TaskResponse>>,
                response: Response<ArrayList<TaskResponse>>
            ) {
                if(response.isSuccessful)
                {
                    tasks = response.body()!!
                    defaultAll()
                    index=0
                    task_next_p.isGone=false
                    generateTask()
                }
            }

            override fun onFailure(call: Call<ArrayList<TaskResponse>>, t: Throwable) {
                Toast.makeText(context,"Nesto nije u redu!", Toast.LENGTH_LONG).show()
            }

        })

    }

    private fun guessTheAnswer() {

        answer_1_p.setOnClickListener() {
            if (tasks[index].correctAnswerIndex == 0) {
                fl_1_p.setBackgroundColor(ContextCompat.getColor(this, R.color.green))
                tv_A_p.setTextColor(ContextCompat.getColor(this, R.color.white))
                tv_A_p.setBackgroundColor(ContextCompat.getColor(this, R.color.green))
                mcv_A_p.strokeColor = ContextCompat.getColor(this, R.color.white)
            } else {
                fl_1_p.setBackgroundColor(ContextCompat.getColor(this, R.color.red))
                tv_A_p.setTextColor(ContextCompat.getColor(this, R.color.white))
                tv_A_p.setBackgroundColor(ContextCompat.getColor(this, R.color.red))
                mcv_A_p.strokeColor = ContextCompat.getColor(this, R.color.white)
                showRightAnswer()
            }
        }

        answer_2_p.setOnClickListener() {
            if (tasks[index].correctAnswerIndex == 1) {
                fl_2_p.setBackgroundColor(ContextCompat.getColor(this, R.color.green))
                tv_B_p.setTextColor(ContextCompat.getColor(this, R.color.white))
                tv_B_p.setBackgroundColor(ContextCompat.getColor(this, R.color.green))
                mcv_B_p.strokeColor = ContextCompat.getColor(this, R.color.white)
            } else {
                fl_2_p.setBackgroundColor(ContextCompat.getColor(this, R.color.red))
                tv_B_p.setTextColor(ContextCompat.getColor(this, R.color.white))
                tv_B_p.setBackgroundColor(ContextCompat.getColor(this, R.color.red))
                mcv_B_p.strokeColor = ContextCompat.getColor(this, R.color.white)
                showRightAnswer()
            }
        }

        answer_3_p.setOnClickListener() {
            if (tasks[index].correctAnswerIndex == 2) {
                fl_3_p.setBackgroundColor(ContextCompat.getColor(this, R.color.green))
                tv_V_p.setTextColor(ContextCompat.getColor(this, R.color.white))
                tv_V_p.setBackgroundColor(ContextCompat.getColor(this, R.color.green))
                mcv_V_p.strokeColor = ContextCompat.getColor(this, R.color.white)
            } else {
                fl_3_p.setBackgroundColor(ContextCompat.getColor(this, R.color.red))
                tv_V_p.setTextColor(ContextCompat.getColor(this, R.color.white))
                tv_V_p.setBackgroundColor(ContextCompat.getColor(this, R.color.red))
                mcv_V_p.strokeColor = ContextCompat.getColor(this, R.color.white)
                showRightAnswer()
            }
        }

        answer_4_p.setOnClickListener() {
            if (tasks[index].correctAnswerIndex == 3) {
                fl_4_p.setBackgroundColor(ContextCompat.getColor(this, R.color.green))
                tv_G_p.setTextColor(ContextCompat.getColor(this, R.color.white))
                tv_G_p.setBackgroundColor(ContextCompat.getColor(this, R.color.green))
                mcv_G_p.strokeColor = ContextCompat.getColor(this, R.color.white)
            } else {
                fl_4_p.setBackgroundColor(ContextCompat.getColor(this, R.color.red))
                tv_G_p.setTextColor(ContextCompat.getColor(this, R.color.white))
                tv_G_p.setBackgroundColor(ContextCompat.getColor(this, R.color.red))
                mcv_G_p.strokeColor = ContextCompat.getColor(this, R.color.white)
                showRightAnswer()
            }
        }

        answer_5_p.setOnClickListener() {
            if (tasks[index].correctAnswerIndex == 4) {
                fl_5_p.setBackgroundColor(ContextCompat.getColor(this, R.color.green))
                tv_D_p.setTextColor(ContextCompat.getColor(this, R.color.white))
                tv_D_p.setBackgroundColor(ContextCompat.getColor(this, R.color.green))
                mcv_D_p.strokeColor = ContextCompat.getColor(this, R.color.white)
            } else {
                fl_5_p.setBackgroundColor(ContextCompat.getColor(this, R.color.red))
                tv_D_p.setTextColor(ContextCompat.getColor(this, R.color.white))
                tv_D_p.setBackgroundColor(ContextCompat.getColor(this, R.color.red))
                mcv_D_p.strokeColor = ContextCompat.getColor(this, R.color.white)
                showRightAnswer()
            }
        }
    }

    private fun showRightAnswer() {
        if(tasks[index].correctAnswerIndex==0)
        {
            fl_1_p.setBackgroundColor(ContextCompat.getColor(this, R.color.green))
            tv_A_p.setTextColor(ContextCompat.getColor(this, R.color.white))
            tv_A_p.setBackgroundColor(ContextCompat.getColor(this, R.color.green))
            mcv_A_p.strokeColor = ContextCompat.getColor(this, R.color.white)
        }
        else if(tasks[index].correctAnswerIndex==1)
        {
            fl_2_p.setBackgroundColor(ContextCompat.getColor(this, R.color.green))
            tv_B_p.setTextColor(ContextCompat.getColor(this, R.color.white))
            tv_B_p.setBackgroundColor(ContextCompat.getColor(this, R.color.green))
            mcv_B_p.strokeColor = ContextCompat.getColor(this, R.color.white)
        }
        else if(tasks[index].correctAnswerIndex==2)
        {
            fl_3_p.setBackgroundColor(ContextCompat.getColor(this, R.color.green))
            tv_V_p.setTextColor(ContextCompat.getColor(this, R.color.white))
            tv_V_p.setBackgroundColor(ContextCompat.getColor(this, R.color.green))
            mcv_V_p.strokeColor = ContextCompat.getColor(this, R.color.white)
        }
        else if(tasks[index].correctAnswerIndex==3)
        {
            fl_4_p.setBackgroundColor(ContextCompat.getColor(this, R.color.green))
            tv_G_p.setTextColor(ContextCompat.getColor(this, R.color.white))
            tv_G_p.setBackgroundColor(ContextCompat.getColor(this, R.color.green))
            mcv_G_p.strokeColor = ContextCompat.getColor(this, R.color.white)
        }
        else
        {
            fl_5_p.setBackgroundColor(ContextCompat.getColor(this, R.color.green))
            tv_D_p.setTextColor(ContextCompat.getColor(this, R.color.white))
            tv_D_p.setBackgroundColor(ContextCompat.getColor(this, R.color.green))
            mcv_D_p.strokeColor = ContextCompat.getColor(this, R.color.white)
        }
    }


    private fun nextTask() {

        task_next_p.setOnClickListener(){

            if(index==tasks.size-2)
                task_next_p.isGone=true

            defaultAll()
            index++

            generateTask()
        }
    }


    private fun defaultAll() {
        fl_1_p.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
        tv_A_p.setTextColor(ContextCompat.getColor(this, R.color.black))
        tv_A_p.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
        mcv_A_p.strokeColor = ContextCompat.getColor(this, R.color.black)

        fl_2_p.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
        tv_B_p.setTextColor(ContextCompat.getColor(this, R.color.black))
        tv_B_p.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
        mcv_B_p.strokeColor = ContextCompat.getColor(this, R.color.black)

        fl_3_p.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
        tv_V_p.setTextColor(ContextCompat.getColor(this, R.color.black))
        tv_V_p.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
        mcv_V_p.strokeColor = ContextCompat.getColor(this, R.color.black)

        fl_4_p.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
        tv_G_p.setTextColor(ContextCompat.getColor(this, R.color.black))
        tv_G_p.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
        mcv_G_p.strokeColor = ContextCompat.getColor(this, R.color.black)

        fl_5_p.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
        tv_D_p.setTextColor(ContextCompat.getColor(this, R.color.black))
        tv_D_p.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
        mcv_D_p.strokeColor = ContextCompat.getColor(this, R.color.black)
    }

    private fun generateTask(){

        var task = tasks[index]

        task_text_p.text = task.taskText
        if(task.taskPicture!="") {

            Picasso.get().load(UtilityFunctions.getFullImagePath(task.taskPicture))
                .into(task_picture)
            cv_task_picture_p.isGone = false
        }
        else
            cv_task_picture_p.isGone = true

        if(task.answersPictures.size==0)
        {
            answer_text_1_p.text=task.answersText[0]
            answer_text_2_p.text=task.answersText[1]
            answer_text_3_p.text=task.answersText[2]
            answer_text_4_p.text=task.answersText[3]
            answer_text_5_p.text=task.answersText[4]

            cv_answer_picture_1_p.isGone=true
            cv_answer_picture_2_p.isGone=true
            cv_answer_picture_3_p.isGone=true
            cv_answer_picture_4_p.isGone=true
            cv_answer_picture_5_p.isGone=true

            answer_text_1_p.isGone=false
            answer_text_2_p.isGone=false
            answer_text_3_p.isGone=false
            answer_text_4_p.isGone=false
            answer_text_5_p.isGone=false


        }
        else
        {
            Picasso.get().load(UtilityFunctions.getFullImagePath(task.answersPictures[0])).into(answer_picture_1_p)
            Picasso.get().load(UtilityFunctions.getFullImagePath(task.answersPictures[1])).into(answer_picture_2_p)
            Picasso.get().load(UtilityFunctions.getFullImagePath(task.answersPictures[2])).into(answer_picture_3_p)
            Picasso.get().load(UtilityFunctions.getFullImagePath(task.answersPictures[3])).into(answer_picture_4_p)
            Picasso.get().load(UtilityFunctions.getFullImagePath(task.answersPictures[4])).into(answer_picture_5_p)

            cv_answer_picture_1_p.isGone=false
            cv_answer_picture_2_p.isGone=false
            cv_answer_picture_3_p.isGone=false
            cv_answer_picture_4_p.isGone=false
            cv_answer_picture_5_p.isGone=false

            answer_text_1_p.isGone=true
            answer_text_2_p.isGone=true
            answer_text_3_p.isGone=true
            answer_text_4_p.isGone=true
            answer_text_5_p.isGone=true

        }

    }

}