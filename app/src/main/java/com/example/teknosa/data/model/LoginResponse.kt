package com.example.teknosa.data.model
import com.google.gson.annotations.SerializedName


data class LoginResponseItem(
    //login jsonnında olan değerlerin başlıklarını tanıttık
    @SerializedName("kullaniciAdi")
    val kullaniciAdi: String,
    @SerializedName("parola")
    val parola: String
)