package com.example.sharedpreference.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.preference.PreferenceManager

class sharedPref(context: Context, private var sharePref: SharedPreferences){

    private lateinit var  sharedPref : SharedPreferences

    var editor:Editor?=null

    fun setPrefString(key:String?,value:String?){
        editor=sharePref.edit()
        editor!!.putString(key,value)
        editor!!.apply()
    }

    fun getPrefString(key:String?): String? {
        return sharePref.getString(key,"")

    }

    fun getPrefBoolean(key:String?): Boolean {
        return sharePref.getBoolean(key,false)

    }
    init {
        sharePref= PreferenceManager.getDefaultSharedPreferences(context)
    }







}