package com.example.teknosa.data.datasource.remote

import com.example.teknosa.Utils.Constants
import com.example.teknosa.data.datasource.TeknosaLoginDataSource
import com.example.teknosa.data.model.LoginResponseItem
import com.example.teknosa.data.model.TeknosaResponseItem
import com.example.teknosa.data.model.Urunler

import com.example.teknosa.data.repository.TeknosaRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface TeknosaService {
    //alıcağımız dataların gitdeki isimlerini verdik.
    //https://jsonplaceholder.typicode.com/users
    @GET("teknosaData.json")
    suspend fun getAllData(): Response <List<TeknosaResponseItem>>
    @GET("teknosaData.json")
    suspend fun getUrunData(): Response <List<Urunler>>
    @GET("teknosaLogin.json")
    suspend fun getLoginData(): Response <List<LoginResponseItem>>

// çekeceğimiz datalara bağlanmamızı sağlar
    companion object  {

        fun build(): TeknosaService {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val okHtppClient =  OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .client(okHtppClient)
                .build()

            return retrofit.create(TeknosaService::class.java)
        }
    }
}