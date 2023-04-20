package com.example.kengur.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kengur.R
import com.example.kengur.dtos.response.LeaderboardResponse
import com.example.kengur.utility.ApiClient
import com.example.kengur.utility.UtilityFunctions
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_leaderboard.view.*
import java.util.ArrayList

class LeaderboardsAdapter (private val leaderboardList:MutableList<LeaderboardResponse>):RecyclerView.Adapter<LeaderboardsAdapter.PostViewHolder>() {

    class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private lateinit var apiClient: ApiClient
    private var rank:Int=4

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        apiClient = ApiClient()
        return PostViewHolder( LayoutInflater.from(parent.context).inflate( R.layout.item_leaderboard, parent, false ) )

    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {

        val currentResult = leaderboardList[position]

        holder.itemView.apply {

            item_l_rank.text = rank.toString()
            rank++
            //Picasso.get().load(UtilityFunctions.getFullImagePath(currentResult.userImage)).into(item_l_image)
            val fullName = currentResult.userDTO.firstName+" "+currentResult.userDTO.lastName
            item_l_name.text = fullName
            item_l_score.text = currentResult.points.toString()

        }


    }

    override fun getItemCount(): Int {
        return leaderboardList.size
    }

    fun setPostList(leaderboardResponseList: ArrayList<LeaderboardResponse>){
        leaderboardList.clear()
        rank=4
        leaderboardList.addAll(leaderboardResponseList)
        notifyDataSetChanged()
    }


}