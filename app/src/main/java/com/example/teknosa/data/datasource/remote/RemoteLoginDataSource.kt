package com.example.teknosa.data.datasource.remote

import com.example.teknosa.Utils.Resource
import com.example.teknosa.data.datasource.TeknosaLoginDataSource
import com.example.teknosa.data.model.LoginResponseItem
import com.example.teknosa.data.model.TeknosaResponseItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteLoginDataSource : TeknosaLoginDataSource{
    override fun getLoginData(): Flow<Resource<List<LoginResponseItem>>> = flow{
        try {

            emit(Resource.Loading())

            val response = TeknosaService.build().getLoginData()
//eğer başarılı veri geliyorsa o sayfaya verileri vermemize  yarıyoor
            if (response.isSuccessful) {

                response.body()?.let {
                    emit(Resource.Success(it))
                }
            }

        } catch (e: Exception) {
            emit(Resource.Error(e))
            e.printStackTrace()
        }
    }
}

