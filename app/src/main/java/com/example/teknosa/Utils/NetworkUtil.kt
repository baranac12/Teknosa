package com.example.teknosa.Utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo


object NetworkUtil {
//bu fonksiyon interneti kontrol ediyor. eğer varsatrue yoksa false döndürüyor
    fun internetKontrol(context: Context, act: Activity):Boolean {
        val connManager = act.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connManager.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        if (isConnected) {
            true
        } else {
            false
        }
        return isConnected
    }

    }