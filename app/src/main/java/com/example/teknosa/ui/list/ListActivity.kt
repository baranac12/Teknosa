package com.example.teknosa.ui.list

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.teknosa.R
import com.example.teknosa.Utils.Constants
import com.example.teknosa.Utils.ItemClickListener
import com.example.teknosa.Utils.ObjectUtil
import com.example.teknosa.Utils.Utils
import com.example.teknosa.data.model.Urunler
import com.example.teknosa.databinding.ActivityListBinding

class ListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListBinding
    private lateinit var listeAdapter: ListeAdapter
    var urunListe: List<Urunler>? = null
    var alınanListe: List<Urunler>? = null
    var urunViewModel: ListViewModel? = null
    var urunler: Urunler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    fun init() {
        val tasinanVeri = getIntent().getStringExtra(Constants.TIKLANAN_CATEGORY);
        alınanListe = ObjectUtil().jsonStringToObje(tasinanVeri);
        Log.e("liste", "liste" + alınanListe)

        listGorunumDegis()
        listSiralama()
        initViewModel()


    }
        //bu fonksiyon alınan listeyi liste adapter ile ekrana basmamıza yarar
    private fun initRecycleView(urunList: List<Urunler>?) {
        binding.apply {
            listeAdapter = ListeAdapter(urunList, applicationContext, object : ItemClickListener {
                override fun onItemClick(position: Int) {
                    urunler = urunList!!.get(position)
                    Log.e("veri", "veri" + urunler)
                    Utils.openDetayActivity(applicationContext, urunler)
                }
            })

            rcvList.adapter = listeAdapter
            rcvList.layoutManager =
                StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL)
        }
    }

        //bu fonksiyon Listview model ile verilerin çekimi başarılı iste listeyi initRecycleVİew fonksiyonu ile listeyi basmamıza yarar.
    private fun initViewModel() {
        urunViewModel = ListViewModel()
        urunViewModel?.apply {
            allTeknosaLiveData?.observe(this@ListActivity, Observer {
                it.run {
                    initRecycleView(alınanListe)

                }
            })
            error?.observe(this@ListActivity, Observer {
                it.run {

                }
            })
            loading?.observe(this@ListActivity, Observer {
                it.run {
                }
            })
        }
    }
        //bu fonksiyon listenin görümünü değiştirmemize yardımcı olur.
    fun listGorunumDegis() {
        binding.apply {
            swList.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    if (swList.isChecked()) {
                        rcvList.layoutManager = LinearLayoutManager(
                            applicationContext,
                            LinearLayoutManager.VERTICAL,
                            false

                        )
                        swList.thumbDrawable=getDrawable(R.drawable.icons8_align_justify_32)

                    } else {
                        rcvList.layoutManager =
                            StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL)
                        swList.thumbDrawable=getDrawable(R.drawable.icons8_grid_view_32)
                    }
                }
            });
        }
    }
// bu fonksiyon listede isme göre sıralamamızı sağlar
    fun listSiralama() {
        binding.apply {
            imgBtnFilter2.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                     urunListe = alınanListe
                    urunListe.let {
                        val builder = AlertDialog.Builder(this@ListActivity)
                        builder.setTitle("Sıralama Türünü Seçiniz")
                        val secim = arrayOf(getString(R.string.listSıralamaArtan), getString(R.string.listSıralamaAzalan))
                        builder.setItems(secim)
                        { dialog, pozisyon->when (pozisyon) {
                                0 -> {
                                    var filter = it!!.sortedBy { it.urunAdi }
                                    listeAdapter.setData(filter);
                                    initRecycleView(filter)
                                    listeAdapter.notifyDataSetChanged();
                                }

                                1 -> {
                                    var filter = it!!.sortedByDescending { it.urunAdi }

                                    listeAdapter.setData(filter);
                                    initRecycleView(filter)

                                    listeAdapter.notifyDataSetChanged();
                                }
                            }

                        }
                        val dialog = builder.create()
                        dialog.show()
                    }

                }
            });

        }
    }
}