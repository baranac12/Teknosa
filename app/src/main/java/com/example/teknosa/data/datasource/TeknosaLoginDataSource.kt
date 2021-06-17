package com.example.teknosa.data.datasource

import com.example.teknosa.Utils.Resource
import com.example.teknosa.data.model.LoginResponseItem

import kotlinx.coroutines.flow.Flow

interface TeknosaLoginDataSource {
    fun getLoginData(): Flow<Resource<List<LoginResponseItem>>>
}