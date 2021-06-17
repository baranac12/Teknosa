package com.example.teknosa.ui.category

import android.content.Context
import android.view.Gravity.apply
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teknosa.Utils.GlideUtil

import com.example.teknosa.Utils.ItemClickListener
import com.example.teknosa.data.model.TeknosaResponseItem
import com.example.teknosa.databinding.CardViewCategoryBinding

class KategoriAdapter(
    var kategoriler: List<TeknosaResponseItem>,
    var context: Context,
    var onItemClickListener: ItemClickListener,
):RecyclerView.Adapter<KategoriAdapter.ViewHolder>()
{
    inner class ViewHolder(val binding: CardViewCategoryBinding): RecyclerView.ViewHolder(binding.root)
    fun setData(yeniData: List<TeknosaResponseItem> ){
        kategoriler =yeniData
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardViewCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }
    override fun getItemCount(): Int {
        return kategoriler.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            binding.apply {
                textView.text=kategoriler.get(position).kategoriAdi
                GlideUtil().resimleriGoster(
                    context,
                    kategoriler.get(position).kategoriResimUrl.toString(),
                    holder.binding.imageCategory
                )
                cardView.setOnClickListener{
                    onItemClickListener.onItemClick(position)
                }
            }
        }
    }
}