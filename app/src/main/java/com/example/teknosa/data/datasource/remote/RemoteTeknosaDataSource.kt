package com.example.teknosa.data.datasource.remote

import com.example.teknosa.Utils.Resource
import com.example.teknosa.data.datasource.TeknosaDataSource
import com.example.teknosa.data.model.TeknosaResponseItem
import com.example.teknosa.data.model.Urunler

import com.example.teknosa.data.repository.TeknosaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteTeknosaDataSource : TeknosaDataSource {
    override fun getAllData():Flow<Resource <List<TeknosaResponseItem>>> = flow{
        try {

            emit(Resource.Loading())

            val response = TeknosaService.build().getAllData()
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