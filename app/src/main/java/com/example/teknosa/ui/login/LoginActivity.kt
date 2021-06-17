package com.example.teknosa.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity.apply
import android.widget.Toast
import androidx.core.view.GravityCompat.apply
import androidx.lifecycle.Observer
import com.bumptech.glide.util.Util
import com.example.teknosa.R
import com.example.teknosa.Utils.Utils
import com.example.teknosa.data.model.LoginResponseItem
import com.example.teknosa.databinding.ActivityLoginBinding
import com.example.teknosa.ui.list.ListViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    var loginViewModel: LoginViewModel? = null
    var loginList: List<LoginResponseItem>? = null
    lateinit var kullaniciAdi: String
     var alinanaKullaniciAdi: String = ""
    lateinit var parola: String
     var alinanParola: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init();
    }

    private fun init() {
        binding.apply {
            btnDontLogin.setOnClickListener {
                Utils.openNextActivity(
                    Utils.ACTIVITY_SEC.CATEGORY_AC,
                    applicationContext,
                    this@LoginActivity
                )

            }
            btnLogin.setOnClickListener{
                alinanaKullaniciAdi = emailText.text.toString()
                alinanParola = parolaText.text.toString()
                initViewModel()
            }
        }

    }

    fun initViewModel() {
        loginViewModel = LoginViewModel()
        loginViewModel?.apply {

            allTeknosaLiveData?.observe(this@LoginActivity, Observer {
                it.run {
                    loginList = it
                        for (login: LoginResponseItem in it!!) {
                                kullaniciAdi = login.kullaniciAdi
                                parola = login.parola

                                if (alinanaKullaniciAdi.equals(kullaniciAdi) && alinanParola.equals(parola)) {

                                    Utils.mesajGonder(this@LoginActivity,getString(R.string.loginGirisTrue),Utils.MESAJ_UZUNLUK.SHORT)
                                    Utils.openNextActivity(
                                        Utils.ACTIVITY_SEC.CATEGORY_AC,
                                        applicationContext,
                                        this@LoginActivity
                                    )
                                }else if(alinanaKullaniciAdi.equals("") || alinanParola.equals("")){

                                    Utils.mesajGonder(this@LoginActivity,getString(R.string.loginGirisNull),Utils.MESAJ_UZUNLUK.SHORT)
                                } else if (alinanaKullaniciAdi.equals(kullaniciAdi)== false && alinanParola.equals(parola)== false)
                                {
                                    Utils.mesajGonder(
                                        this@LoginActivity,
                                        getString(R.string.loginGirisFalse),
                                        Utils.MESAJ_UZUNLUK.LONG
                                    )
                                }


                        }
                    }


                error?.observe(this@LoginActivity, Observer {
                    it.run {

                    }
                })
                loading?.observe(this@LoginActivity, Observer {
                    it.run {
                    }
                })
            })

        }


    }
}

