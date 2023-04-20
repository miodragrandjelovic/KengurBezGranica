package com.example.kengur.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.kengur.R
import com.example.kengur.adapters.ChooseTestAdapter
import com.example.kengur.adapters.LeaderboardsAdapter
import com.example.kengur.models.ChooseTest
import kotlinx.android.synthetic.main.activity_choose_test.*
import kotlinx.android.synthetic.main.activity_leaderboard.*

class ChooseTestActivity : AppCompatActivity() {

    private lateinit var chooseTestAdapter: ChooseTestAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_test)

        initChooseTest()

    }

    private fun setChooseTestRV() {
        chooseTestAdapter = ChooseTestAdapter(mutableListOf())
        rv_choose_test.adapter = chooseTestAdapter
        rv_choose_test.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        (rv_choose_test.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
    }


    private fun initChooseTest() {

        var testList:ArrayList<ChooseTest> = ArrayList()

        testList.add(0,ChooseTest("Nivo 1","Za prvi razred osnovne škole"))
        testList.add(1,ChooseTest("Nivo 2","Za drugi razred osnovne škole"))
        testList.add(2,ChooseTest("Nivo 3","Za treći i četvrti razred osnovne škole"))
        testList.add(3,ChooseTest("Nivo 4","Za peti i šesti razred osnovne škole"))
        testList.add(4,ChooseTest("Nivo 5","Za sedmi i osmi razred osnovne škole"))
        testList.add(5,ChooseTest("Nivo 6","Za prvi i drugi razred srednje škole"))
        testList.add(6,ChooseTest("Nivo 7","Za treći i četvrti razred srednje škole"))

        setChooseTestRV()
        chooseTestAdapter.setPostList(testList)

    }
}