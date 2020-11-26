package com.example.santecoffee.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.santecoffee.R
import com.example.santecoffee.adapter.FarmerAdapter
import com.example.santecoffee.db.Farmer
import com.example.santecoffee.ui.addFarmer.AddFarmerViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private val addFarmerViewModel: AddFarmerViewModel by viewModels()

    private lateinit var farmer_adapter: FarmerAdapter


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        farmer_recycler_view.apply {
            farmer_adapter = FarmerAdapter()
            adapter = farmer_adapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        homeViewModel.farmers_list.observe(viewLifecycleOwner, Observer {
            farmer_adapter.submitList(it)
        })

        homeViewModel.farmers_from_api.observe(viewLifecycleOwner, Observer {
            it.forEach {farmer_list_item ->
                addFarmerViewModel.insertFarmer(Farmer(
                    farmer_list_item.name,
                    farmer_list_item.gender,
                    "",
                    "",
                    farmer_list_item.phone,
                    0L,
                    ""
                ))
            }
        })
    }
}