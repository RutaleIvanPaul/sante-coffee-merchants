package com.example.santecoffee.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.santecoffee.R
import com.example.santecoffee.db.Farmer
import kotlinx.android.synthetic.main.farmer_item.view.*
import kotlinx.android.synthetic.main.fragment_add_farmer.view.*

class FarmerAdapter : RecyclerView.Adapter<FarmerAdapter.FarmerViewHolder>() {

    inner class FarmerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    val diffCallBack = object: DiffUtil.ItemCallback<Farmer>(){
        override fun areItemsTheSame(oldItem: Farmer, newItem: Farmer): Boolean {
            return oldItem.Id == newItem.Id
        }

        override fun areContentsTheSame(oldItem: Farmer, newItem: Farmer): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    val differ = AsyncListDiffer(this,diffCallBack)

    fun submitList(list: List<Farmer>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FarmerViewHolder {
        return FarmerViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(
                    R.layout.farmer_item,
                    parent,
                    false
                )
        )
    }

    override fun onBindViewHolder(holder: FarmerViewHolder, position: Int) {
        val farmer = differ.currentList[position]
        holder.itemView.apply {
            farmer_name_rv.text = farmer.name
            gender_rv.text = farmer.gender
            phone_rv.text = farmer.phoneNumber
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}
