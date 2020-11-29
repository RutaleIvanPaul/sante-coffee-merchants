package com.laboremus.santecoffee.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.laboremus.santecoffee.R
import com.laboremus.santecoffee.model.AuditAdapterItem
import kotlinx.android.synthetic.main.audit_item.view.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class AuditAdapter @Inject constructor(
        val di_context: Context
) : RecyclerView.Adapter<AuditAdapter.AuditViewHolder>() {

    var audits = mutableListOf<AuditAdapterItem>()

    val dateFormat = SimpleDateFormat("dd.M.yy", Locale.getDefault())

    inner class AuditViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuditAdapter.AuditViewHolder {
        return AuditViewHolder(
                LayoutInflater
                        .from(parent.context)
                        .inflate(
                                R.layout.audit_item,
                                parent,
                                false
                        )
        )
    }

    override fun onBindViewHolder(holder: AuditAdapter.AuditViewHolder, position: Int) {
        val audit_item = audits[position]
        holder.itemView.apply {
            audit_name_rv.text = audit_item.name
            audit_lastupdateby_rv.text = "last updated by:${audit_item.lastUpdatedBY}"
            audit_date_rv.text = dateFormat.format(audit_item.timestamp)
        }
    }

    override fun getItemCount(): Int {
        return audits.size
    }
}