package com.laboremus.santecoffee.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.laboremus.santecoffee.R
import com.laboremus.santecoffee.db.Farmer
import com.laboremus.santecoffee.ui.editFarmer.EditFarmerActivity
import kotlinx.android.synthetic.main.farmer_item.view.*
import java.util.*
import javax.inject.Inject

class FarmerAdapter @Inject constructor(
    val di_context: Context
) : RecyclerView.Adapter<FarmerAdapter.FarmerViewHolder>(), Filterable {

    inner class FarmerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }


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

    var farmers = mutableListOf<Farmer>()
    var filterList = farmers

    override fun onBindViewHolder(holder: FarmerViewHolder, position: Int) {
        val farmer = filterList[position]
        holder.itemView.apply {
            farmer_name_rv.text = farmer.name
            gender_rv.text = farmer.gender
            phone_rv.text = farmer.phoneNumber
            bc_url_rv.text = farmer.birthCertificateUrl

            farmer_item_layout.setOnClickListener {
                startEditActivity(farmer)
            }
        }
    }

    override fun getItemCount(): Int {
        return filterList.size
    }

    fun startEditActivity(farmer: Farmer){
        val intent = Intent(di_context, EditFarmerActivity::class.java).apply {
            this.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            putExtra("farmer_parcel", farmer)
            putExtra("id",farmer.Id)
        }
        di_context.startActivity(intent)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                filterList = if (charSearch.isEmpty()) {
                   farmers
                } else {
                    val resultList = mutableListOf<Farmer>()
                    for (farmer in farmers) {
                        if (farmer.name.toLowerCase(Locale.ROOT)
                                        .contains(charSearch.toLowerCase(Locale.ROOT))
                        ) {
                            resultList.add(farmer)
                        }
                    }
                    resultList
                }

                val filterResults = FilterResults()
                filterResults.values = filterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterList = results?.values as MutableList<Farmer>
                notifyDataSetChanged()
            }
        }
    }


}
