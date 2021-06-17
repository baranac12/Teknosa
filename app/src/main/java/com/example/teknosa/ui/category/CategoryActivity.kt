package com.example.teknosa.ui.category

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.teknosa.R
import com.example.teknosa.Utils.ItemClickListener
import com.example.teknosa.Utils.Utils
import com.example.teknosa.data.model.TeknosaResponseItem
import com.example.teknosa.data.model.Urunler
import com.example.teknosa.databinding.ActivityCategoryBinding

class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding
    private lateinit var  kategoriAdapter: KategoriAdapter

    var  kategoriListesi : List<TeknosaResponseItem>? = null
    var kategoriViewModel : CategoryViewModel? = null
    var urunList :List<Urunler>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()

    }

     fun init() {

         initViewModel()

        binding.apply {
         searchVCategory.setOnQueryTextListener((object : SearchView.OnQueryTextListener {
             override fun onQueryTextSubmit(query: String?): Boolean {
                 return false
             }

             override fun onQueryTextChange(newText: String?): Boolean {
                 kategoriFilter(newText)
                 return false
             }

         }))
        }
    }
     fun initRecycleView(kategoriListe: List<TeknosaResponseItem>){
        binding.apply {
           kategoriAdapter = KategoriAdapter(
               kategoriListe,
               applicationContext,
               object : ItemClickListener {
                   override fun onItemClick(position: Int) {
                       urunList = kategoriListe.get(position).urunler
                       Utils.openListActivity(applicationContext, urunList!!)

                   }

               })

            rcvCategori.adapter=kategoriAdapter
            rcvCategori.layoutManager=StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL)
        }
    }
    fun kategoriFilter(name: String?){
            name?.let {
                kategoriListesi?.let {
                    var filterCategory = it.filter { it.kategoriAdi!!.toLowerCase().contains(name) }
                    kategoriAdapter.setData(filterCategory)
                    initRecycleView(filterCategory)

                }
            }
    }
    fun initViewModel(){

        kategoriViewModel = CategoryViewModel()
        kategoriViewModel?.apply {
            allTeknosaLiveData?.observe(this@CategoryActivity, Observer {
                it.run {


                    kategoriListesi = it
                    initRecycleView(it)

                }
            })
            error?.observe(this@CategoryActivity, Observer {
                it.run {

                }
            })
            loading?.observe(this@CategoryActivity, Observer {
                it.run {
                }
            })
        }
    }



    override fun onBackPressed() {
        Utils.alertBas(
            this,
            getString(R.string.onBackPressMessage),
            getString(R.string.onBackPressButton1),
            getString(
                R.string.onBackPressButton2
            )
        )
    }
}