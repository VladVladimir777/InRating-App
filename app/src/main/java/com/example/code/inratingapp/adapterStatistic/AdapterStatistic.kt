package com.example.code.inratingapp.adapterStatistic

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.code.inratingapp.R
import kotlinx.android.synthetic.main.item_list_statistic.view.*

class AdapterStatistic(var data: List<ItemStatistic>) :
    RecyclerView.Adapter<AdapterStatistic.ViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_list_statistic, viewGroup, false)
        )
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.bind(data[i])
    }


    override fun getItemCount(): Int {
        return data.size
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: ItemStatistic) = with(itemView) {
            itemView.tvItemStatisticTitle.text = item.title
            itemView.rvListUsers.layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            itemView.rvListUsers.adapter = item.adapterUsers
        }
    }


    internal fun getItem(id: Int): ItemStatistic {
        return data[id]
    }

}