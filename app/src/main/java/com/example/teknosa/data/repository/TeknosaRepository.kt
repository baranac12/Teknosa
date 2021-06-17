package com.example.teknosa.data.repository

import android.util.Log
import com.example.teknosa.Utils.Resource


import com.example.teknosa.data.datasource.TeknosaDataSource
import com.example.teknosa.data.datasource.TeknosaLoginDataSource
import com.example.teknosa.data.datasource.TeknosaUrunlerDataSource
import com.example.teknosa.data.datasource.remote.RemoteLoginDataSource
import com.example.teknosa.data.datasource.remote.RemoteTeknosaDataSource
import com.example.teknosa.data.datasource.remote.RemoteTeknosaUrunlerDataSource
import com.example.teknosa.data.model.LoginResponseItem
import com.example.teknosa.data.model.TeknosaResponseItem
import com.example.teknosa.data.model.Urunler

import kotlinx.coroutines.flow.Flow

class TeknosaRepository {
    private var teknosaDataSource: TeknosaDataSource?=null
    private var teknosaUrunlerDataSource: TeknosaUrunlerDataSource?=null
    private var loginDataSource : TeknosaLoginDataSource ?=null

    init {
        teknosaDataSource= RemoteTeknosaDataSource()
        teknosaUrunlerDataSource= RemoteTeknosaUrunlerDataSource()
        loginDataSource= RemoteLoginDataSource()
    }

    fun getAllData(): Flow<Resource <List<TeknosaResponseItem>>>
    {
        return teknosaDataSource!!.getAllData();
    }
    fun getUrunlerData(): Flow<Resource <List<Urunler>>>
    {
        return teknosaUrunlerDataSource!!.getUrunlerData();
    }
    fun getLoginData() : Flow<Resource<List<LoginResponseItem>>> {
        return loginDataSource!!.getLoginData();
    }
}