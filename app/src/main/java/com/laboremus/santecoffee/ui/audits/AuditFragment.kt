package com.laboremus.santecoffee.ui.audits

import android.os.Bundle
import android.util.Log
import android.util.Log.DEBUG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.laboremus.santecoffee.R
import com.laboremus.santecoffee.adapter.AuditAdapter
import com.laboremus.santecoffee.model.AuditAdapterItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_audits.*
import javax.inject.Inject

@AndroidEntryPoint
class AuditFragment : Fragment() {

    @Inject lateinit var audit_adapter: AuditAdapter

    private val auditViewModel: AuditViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_audits, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        audits_rv.apply {
            adapter = audit_adapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        auditViewModel.getFarmersAndAudits().observe(viewLifecycleOwner, Observer { farmers_with_audits ->
            val audits = mutableListOf<AuditAdapterItem>()
            Log.d("AUDITS",farmers_with_audits.toString())
            if (farmers_with_audits[0].audits.isEmpty()) {
                audit_progress_bar.visibility = View.GONE
            }
            else {
                farmers_with_audits.forEach { one_farmer_with_audits ->
                    one_farmer_with_audits.audits.forEach { one_farmers_audit ->

                        audits.add(AuditAdapterItem(one_farmer_with_audits.farmer.name,
                                one_farmers_audit.updatedBy,
                                one_farmers_audit.timestamp))
                    }
                }

                audit_adapter.audits.clear()
                audit_adapter.audits.addAll(audits)
                audit_adapter.notifyDataSetChanged()
                audit_progress_bar.visibility = View.GONE
                no_audits.visibility = View.GONE
            }

        })
    }
}