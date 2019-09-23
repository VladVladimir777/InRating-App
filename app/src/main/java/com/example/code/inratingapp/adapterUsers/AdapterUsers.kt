package com.example.code.inratingapp.adapterUsers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.code.inratingapp.api.apiData.Data
import kotlinx.android.synthetic.main.item_list_users.view.*
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.code.inratingapp.R

class AdapterUsers(clickListener: ItemClickListener? = null) :
    PagedListAdapter<Data, AdapterUsers.ViewHolder>(diffUtilCallback) {


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_list_users, viewGroup, false)
        )
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        getItem(i)?.let { viewHolder.bind(it) }
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Data) = with(itemView) {
            Glide
                .with(itemView)
                .load(item.avatar_image?.url_small)
                .transform(RoundedCorners(18))
                .into(itemView.ivUserAvatar)
            itemView.tvUserNickName.text = item.nickname
        }
    }


    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }


    companion object {
        val diffUtilCallback = object : DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem == newItem
            }
        }
    }

}