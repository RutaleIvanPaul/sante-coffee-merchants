package com.laboremus.santecoffee.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.laboremus.santecoffee.R
import com.laboremus.santecoffee.adapter.FarmerAdapter
import com.laboremus.santecoffee.db.Farmer
import com.laboremus.santecoffee.ui.addFarmer.AddFarmerViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment: Fragment() {

    @Inject lateinit var farmer_adapter: FarmerAdapter

    private val homeViewModel: HomeViewModel by viewModels()
    private val addFarmerViewModel: AddFarmerViewModel by viewModels()


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
            adapter = farmer_adapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        homeViewModel.farmers_list.observe(viewLifecycleOwner, Observer {
            farmer_adapter.farmers.addAll(it)
            farmer_adapter.notifyDataSetChanged()
            home_progressBar.visibility = View.GONE
        })

        homeViewModel.farmers_from_api.observe(viewLifecycleOwner, Observer {
            /*
            * At this point, we are supposed to first upload the table records that have not yet
            * been synced with the remote Database, then clear the table. That will be done when the
            * endpoints are available
            * */
            val farmers_from_api_list = mutableListOf<Farmer>()
            /*
            * Since the API results keep changing, for demonstration purposes, I will not clear the
            * table here.
            * */
            it.forEach {farmer_list_item ->
                farmers_from_api_list.add(Farmer(
                    farmer_list_item.name,
                    farmer_list_item.gender,
                    "No Birth Certificate.",
                    "",
                    farmer_list_item.phone,
                    0L,
                    ""
                ))
            }
            homeViewModel.addAllFarmers(farmers_from_api_list)
        })

        searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                farmer_adapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                farmer_adapter.filter.filter(newText)
                return false
            }
        })

        searchview.requestFocus()
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.farmers_list.observe(viewLifecycleOwner, Observer {
            farmer_adapter.farmers.clear()
            farmer_adapter.farmers.addAll(it)
            farmer_adapter.notifyDataSetChanged()
            home_progressBar.visibility = View.GONE
        })
    }
}