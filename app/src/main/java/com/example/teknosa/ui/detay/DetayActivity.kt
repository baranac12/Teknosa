package com.example.teknosa.ui.detay

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.teknosa.R
import com.example.teknosa.Utils.*
import com.example.teknosa.data.model.Urunler
import com.example.teknosa.databinding.ActivityDetayBinding
import com.example.teknosa.ui.list.ListViewModel
import com.example.teknosa.ui.list.ListeAdapter


class DetayActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetayBinding
    var alınanListe: List <Urunler>? = null
    var urunler: Urunler? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }
    fun init() {
        val tasinanVeri = getIntent().getStringExtra(Constants.TIKLANAN_URUN);
        urunler = ObjectUtil().jsonStringToObje2(tasinanVeri);
        Log.e("liste", "liste" + alınanListe)
        binding.apply {
            btnSatinAl.text = resources.getText(R.string.detayButton)
            txtAd.text=urunler!!.urunAdi
            txtAciklama.text=urunler!!.aciklama
            txtFiyat.text=urunler!!.fiyati
            GlideUtil().resimleriGoster(applicationContext,urunler!!.urunResimUrl,imgKapak)
        }

    }
}