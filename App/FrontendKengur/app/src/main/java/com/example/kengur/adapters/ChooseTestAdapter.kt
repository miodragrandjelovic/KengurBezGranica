package com.example.kengur.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kengur.R
import com.example.kengur.activities.TestActivity
import com.example.kengur.models.ChooseTest
import kotlinx.android.synthetic.main.item__choose_test.view.*
import java.util.ArrayList


class ChooseTestAdapter (private val chooseTestList:MutableList<ChooseTest>): RecyclerView.Adapter<ChooseTestAdapter.PostViewHolder>() {

    class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item__choose_test, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {

        val chosenTest = chooseTestList[position]

        holder.itemView.apply {

            tv_level.text=chosenTest.level
            tv_description.text=chosenTest.description

            ll_item.setOnClickListener(){
                val intent = Intent(context, TestActivity::class.java)
                intent.putExtra("Class", position.toString())
                context.startActivity(intent)

            }

        }
    }

    override fun getItemCount(): Int {
        return chooseTestList.size
    }


    fun setPostList(testList: ArrayList<ChooseTest>){
        chooseTestList.clear()
        chooseTestList.addAll(testList)
        notifyDataSetChanged()
    }


}