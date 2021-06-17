package com.example.teknosa.Utils


import com.example.teknosa.data.model.TeknosaResponseItem
import com.example.teknosa.data.model.Urunler
import com.google.gson.Gson

class ObjectUtil {

//bu fonksiyon objeyi stringe çeviriyor
    fun ObjeToJsonString(liste: List<Urunler>?): String? {
        val gson = Gson()
        return gson.toJson(liste)
    }
    fun ObjeToJsonString2(liste: Urunler?): String? {
        val gson = Gson()
        return gson.toJson(liste)
    }
    fun jsonStringToObje2(jsonString: String?): Urunler? {
        val gson = Gson()
        return gson.fromJson(jsonString , Urunler::class.java)
    }

    fun jsonStringToObje(jsonString: String?): List<Urunler>? {
        val gson = Gson()
        return gson.fromJson(jsonString , Array<Urunler>::class.java).toList()
    }
}