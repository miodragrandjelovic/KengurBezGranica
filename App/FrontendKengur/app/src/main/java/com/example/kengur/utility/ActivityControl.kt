package com.example.kengur.utility

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.kengur.models.UserData

object ActivityControl {

    /*public fun handleUserSignedIn(activity: Activity?, context: Context, sessionManager: SessionManager, savedInstanceState: Bundle?){
        var user: UserData? = sessionManager.fetchUserData()
        if(user != null){
            val intent = Intent(context, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            activity?.finishAffinity()
            ContextCompat.startActivity(context, intent, savedInstanceState)
        }
    }

    public fun handleUserNotSignedIn(activity: Activity?, context: Context, sessionManager: SessionManager, savedInstanceState: Bundle?){
        var user: UserData? = sessionManager.fetchUserData()
        if(user == null){
            val intent = Intent(context, MainActivity::class.java)
            ContextCompat.startActivity(context, intent, savedInstanceState)
        }
    }*/

}