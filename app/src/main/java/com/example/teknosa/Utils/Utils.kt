package com.example.teknosa.Utils

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.example.teknosa.R
import com.example.teknosa.data.model.Urunler
import com.example.teknosa.ui.category.CategoryActivity
import com.example.teknosa.ui.detay.DetayActivity
import com.example.teknosa.ui.list.ListActivity
import com.example.teknosa.ui.login.LoginActivity


object Utils {

    //enumlardan seçilen değere göre sayfa yönlendirmeye yara
    fun openNextActivity(activity_sec: ACTIVITY_SEC, context: Context, activity: Activity) {
        if (activity_sec == ACTIVITY_SEC.CATEGORY_AC){
        val secondActivityIntent = Intent(context, CategoryActivity::class.java)
            secondActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(context, secondActivityIntent, null)
            activity.finish()
        }else if (activity_sec == ACTIVITY_SEC.LOGIN_AC){
            val secondActivityIntent = Intent(context, LoginActivity::class.java)
            secondActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(context, secondActivityIntent, null)
            activity.finish()}
       }
    //list activitye obje gönderir ve list actiivtyi açar
    fun openListActivity(context: Context, urunList: List<Urunler>){
        val secondActivityIntent = Intent(context, ListActivity::class.java)
        secondActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        var objectUtil = ObjectUtil();
        var veri = objectUtil.ObjeToJsonString(urunList)
        secondActivityIntent.putExtra(Constants.TIKLANAN_CATEGORY, veri)
        ContextCompat.startActivity(context, secondActivityIntent, null)
    }
    //detay activitye obje gönderir ve detay actiivtyi açar
    fun openDetayActivity(context: Context, urunList: Urunler?){
        val secondActivityIntent = Intent(context, DetayActivity::class.java)
        secondActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        var objectUtil = ObjectUtil();
        var veri = objectUtil.ObjeToJsonString2(urunList)
        secondActivityIntent.putExtra(Constants.TIKLANAN_URUN, veri)
        ContextCompat.startActivity(context, secondActivityIntent, null)
    }


    //enumlardan alınan mesaj uzunluuğu ve kullanıcdan alınan string ile toast basar
    fun mesajGonder(activity: Activity, mesaj: String, mesajUzunluk: MESAJ_UZUNLUK){
        if(mesajUzunluk == MESAJ_UZUNLUK.SHORT){
            Toast.makeText(activity,mesaj,Toast.LENGTH_SHORT).show()
        }else if(mesajUzunluk == MESAJ_UZUNLUK.LONG){
            Toast.makeText(activity,mesaj,Toast.LENGTH_LONG).show()
        }
    }
    //ekrana alert basmamıza yarar
    fun alertBas(activity: Activity,mesaj : String, button1: String,buton2 :String){
        val builder = AlertDialog.Builder(activity)
        builder.setCancelable(false)
        builder.setMessage(mesaj)
        builder.setPositiveButton(button1) { dialogInterface: DialogInterface, i: Int -> activity.finish()}
        builder.setNegativeButton(
        buton2
        ) { dialog, which -> dialog.cancel() }
        val alert = builder.create()
        alert.show()
    }


    enum class ACTIVITY_SEC{
        LOGIN_AC,
        CATEGORY_AC,
    }
    enum class MESAJ_UZUNLUK{
        SHORT,
        LONG
    }

}