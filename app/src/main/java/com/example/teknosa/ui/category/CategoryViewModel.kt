package com.example.teknosa.ui.category

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.teknosa.Utils.ResourceStatus
import com.example.teknosa.data.model.TeknosaResponseItem

import com.example.teknosa.data.repository.TeknosaRepository
import kotlinx.coroutines.launch

class CategoryViewModel: ViewModel() {
    private  val teknosaRepository: TeknosaRepository = TeknosaRepository()
    init {
        getAllData()
    }
    var allTeknosaLiveData = MutableLiveData<List<TeknosaResponseItem>>()
    var error =    MutableLiveData<Throwable>()
    var loading   : MutableLiveData<Boolean>? = MutableLiveData()

    fun getAllData()  = viewModelScope.launch {
        teknosaRepository.getAllData()
            .asLiveData(viewModelScope.coroutineContext).observeForever {
                when(it.status) {
                    ResourceStatus.LOADING -> {
                        loading?.postValue(true)
                    }
                    ResourceStatus.SUCCESS -> {
                        allTeknosaLiveData?.postValue(it.data!!)
                        loading?.postValue(false)
                    }
                    ResourceStatus.ERROR -> {
                        Log.e("ERROR", "${it.throwable}")
                        error?.postValue(it.throwable!!)
                        loading?.postValue(false)
                    }
                }
            }
    }
}