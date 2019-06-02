package com.example.android.kotlinproject

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.kotlinproject.model.PetsInfo
import kotlinx.android.synthetic.main.row_item.view.*

class OwnerAndPetInfoAdapter(val context: Context, val petsOwnersData: List<PetsInfo>?): RecyclerView.Adapter<OwnerAndPetInfoAdapter.MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_item, parent, false )
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (petsOwnersData != null) {
            return petsOwnersData.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val ownerInfo = this.petsOwnersData!![position]
        holder.bind(ownerInfo)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(ownerInfo: PetsInfo) {
            itemView.tv_cat_name.text = ownerInfo.name
        }

    }
}