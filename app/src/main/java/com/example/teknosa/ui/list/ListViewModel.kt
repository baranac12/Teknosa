package com.example.teknosa.ui.list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope

import com.example.teknosa.Utils.Resource
import com.example.teknosa.Utils.ResourceStatus

import com.example.teknosa.data.model.TeknosaResponseItem
import com.example.teknosa.data.model.Urunler
import com.example.teknosa.data.repository.TeknosaRepository
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {

    private  val teknosaRepository: TeknosaRepository =TeknosaRepository()

    init {
        getUrunData()
    }

    var allTeknosaLiveData = MutableLiveData<List<Urunler>>()
    var error =    MutableLiveData<Throwable>()
    var loading   : MutableLiveData<Boolean>? = MutableLiveData()

    fun getUrunData()  = viewModelScope.launch {

        teknosaRepository.getUrunlerData()

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