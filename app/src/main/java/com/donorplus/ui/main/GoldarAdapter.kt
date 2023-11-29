package com.donorplus.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.donorplus.data.DataDarah
import com.donorplus.databinding.ItemGoldarBinding
import com.donorplus.databinding.ItemMainBinding

class GoldarAdapter(private val darah: ArrayList<DataDarah>): RecyclerView.Adapter<GoldarAdapter.ViewHolder>() {
    class ViewHolder (val binding: ItemMainBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemMainBinding.inflate(LayoutInflater.from(parent.context),parent, false))

    override fun getItemCount(): Int = darah.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = darah[position]
        holder.apply {
            binding.apply {
                tvItemExp.text = "Expired Sebelum ${currentItem.expiredDate}"
                tvItemGolonganDarah.text = currentItem.tipeDarah
                tvItemLocation.text = currentItem.location
            }
        }
    }
}