package com.example.teknosa.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teknosa.R
import com.example.teknosa.Utils.GlideUtil
import com.example.teknosa.Utils.ItemClickListener
import com.example.teknosa.Utils.Utils
import com.example.teknosa.data.model.TeknosaResponseItem
import com.example.teknosa.data.model.Urunler
import com.example.teknosa.databinding.CardViewCategoryBinding
import com.example.teknosa.databinding.CardViewListBinding

class ListeAdapter (
    var urunler: List<Urunler>?,
    var context: Context,
    var onItemClickListener: ItemClickListener,
):RecyclerView.Adapter<ListeAdapter.ViewHolder>()
    {
        inner class ViewHolder(val binding: CardViewListBinding): RecyclerView.ViewHolder(binding.root)

        fun setData(yeniData: List<Urunler> ){
            urunler =yeniData

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val binding = CardViewListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return ViewHolder(binding)
        }

        override fun getItemCount(): Int {
            return urunler!!.size
        }
        //bu fonksiyon çekilen data seçilen kategoriye göre ürünleri getirir ve basar
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            with(holder){
                binding.apply {
                    txtList.text=urunler!![position].urunAdi
                    txtTutar.text = urunler!![position].fiyati
                    GlideUtil().resimleriGoster(
                        context,
                        urunler!!.get(position).urunResimUrl,
                        holder.binding.imageList
                    )

                    cardViewList.setOnClickListener{
                        onItemClickListener.onItemClick(position)

                    }

                }
            }
        }
}
