package com.example.kengur.utility

import com.example.kengur.dtos.request.ResultRequest

interface ActivityTransferStorage {

    companion object{

        @JvmStatic
        lateinit var leaderboardScore:ResultRequest

    }

}