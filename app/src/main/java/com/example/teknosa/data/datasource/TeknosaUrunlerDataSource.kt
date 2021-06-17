package com.example.teknosa.data.datasource

import com.example.teknosa.Utils.Resource
import com.example.teknosa.data.model.Urunler
import kotlinx.coroutines.flow.Flow

interface TeknosaUrunlerDataSource {
    fun getUrunlerData(): Flow<Resource<List<Urunler>>>
}