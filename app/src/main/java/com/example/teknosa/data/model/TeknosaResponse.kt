package com.example.teknosa.data.model
import com.google.gson.annotations.SerializedName

data class TeknosaResponseItem(
    @SerializedName("kategoriAdi")
    val kategoriAdi: String,
    @SerializedName("kategoriResimUrl")
    val kategoriResimUrl: String,
    @SerializedName("urunler")
    val urunler: List<Urunler>
)

data class Urunler(
    @SerializedName("aciklama")
    val aciklama: String,
    @SerializedName("fiyati")
    val fiyati: String,
    @SerializedName("urunAdi")
    val urunAdi: String,
    @SerializedName("urunResimUrl")
    val urunResimUrl: String
)