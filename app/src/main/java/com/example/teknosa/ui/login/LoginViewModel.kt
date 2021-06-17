package com.example.teknosa.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.teknosa.Utils.ResourceStatus
import com.example.teknosa.data.model.LoginResponseItem
import com.example.teknosa.data.model.Urunler
import com.example.teknosa.data.repository.TeknosaRepository
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    private  val teknosaRepository: TeknosaRepository = TeknosaRepository()

    init {
        getLoginData()
    }

    var allTeknosaLiveData = MutableLiveData<List<LoginResponseItem>>()
    var error =    MutableLiveData<Throwable>()
    var loading   : MutableLiveData<Boolean>? = MutableLiveData()
//bu fonksiyonda alınan datanın çekimini, yüklenmesini ve hatalarını görmemize yarar.
    fun getLoginData()  = viewModelScope.launch {

        teknosaRepository.getLoginData()

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