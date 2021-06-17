package com.example.teknosa.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.provider.Settings
import android.view.Gravity.apply
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat.apply
import com.example.teknosa.Utils.NetworkUtil
import android.util.Log
import android.widget.Toast

import androidx.lifecycle.Observer
import com.example.teknosa.R
import com.example.teknosa.Utils.Utils
import com.example.teknosa.ui.list.ListViewModel
import com.example.teknosa.ui.login.LoginActivity


class SplashActivity : AppCompatActivity() {

    var listViewModel: ListViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        init();
    }

    private fun init() {

        //bu fonksiyon 3 saniyelik bir timer başlatır ve bitince internetKontrol fonksiyonunu çalıştırır.
        val countDownTimer: CountDownTimer = object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                Utils.openNextActivity(Utils.ACTIVITY_SEC.LOGIN_AC,applicationContext,this@SplashActivity)
            }
        }
        countDownTimer.start()
    }


        // bu fonksiyon internet kontrolü yapar. eğer internet yok ise alert dialog ile bilgi verir ayarlar git ve çık seçeneklerini kullanıca sunar.
    private fun internetKontrol() {
        if (NetworkUtil.internetKontrol(applicationContext, this)) {
            Utils.openNextActivity(Utils.ACTIVITY_SEC.LOGIN_AC,applicationContext,this)
        } else {
            val builder = AlertDialog.Builder(this)
            builder.setCancelable(false)
            builder.setTitle(resources.getString(R.string.internetUyariBaslik))
            builder.setMessage(resources.getString(R.string.internetUyariMesaj))
            builder.setPositiveButton(
                resources.getString(R.string.internetUyariButon1)
            ) { dialog, which -> startActivity(Intent(Settings.ACTION_WIRELESS_SETTINGS)) }
            builder.setNegativeButton(
                resources.getString(R.string.internetUyariButon2)
            ) { dialog, which -> finish() }
            val alert = builder.create()
            alert.show()
        }
    }
}