package com.example.teknosa.data.datasource


import com.example.teknosa.Utils.Resource
import com.example.teknosa.data.model.TeknosaResponseItem
import com.example.teknosa.data.model.Urunler

import com.example.teknosa.data.repository.TeknosaRepository
import kotlinx.coroutines.flow.Flow

interface TeknosaDataSource {

    fun getAllData(): Flow<Resource <List<TeknosaResponseItem>>>

}