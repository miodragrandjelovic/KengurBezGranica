package com.example.kengur.models

import com.example.kengur.utility.SessionManager
import com.google.gson.annotations.SerializedName

data class UserData(

    @SerializedName(SessionManager.FIRSTNAME_KEY)
    var firstName: String,

    @SerializedName(SessionManager.LASTNAME_KEY)
    var lastName: String,

    @SerializedName(SessionManager.EMAIL_KEY)
    var email: String,

    @SerializedName(SessionManager.EXP_KEY)
    var exp: Int

)
