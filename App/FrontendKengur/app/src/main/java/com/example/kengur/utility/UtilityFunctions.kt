package com.example.kengur.utility

object UtilityFunctions {

    fun getFullImagePath(imagePath: String): String{
        return Constants.BASE_URL + "/" + imagePath
    }
}