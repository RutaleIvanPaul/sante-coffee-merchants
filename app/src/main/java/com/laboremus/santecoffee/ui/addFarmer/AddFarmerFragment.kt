package com.laboremus.santecoffee.ui.addFarmer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.laboremus.santecoffee.R
import com.laboremus.santecoffee.db.Farmer
import com.laboremus.santecoffee.util.DISPLAY_NAME
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_add_farmer.*
import java.util.*

@AndroidEntryPoint
class AddFarmerFragment : Fragment() {

    private val addFarmerViewModel: AddFarmerViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_add_farmer, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addFarmerbutton.setOnClickListener {
            addFarmerViewModel.insertFarmer(
                Farmer(
                    name.text.toString(),
                    gender.text.toString(),
                    birthCertificate.text.toString(),
                    nin.text.toString(),
                    phone.text.toString(),
                    Calendar.getInstance().timeInMillis,
                    DISPLAY_NAME
                )
            )

            name.text?.clear()
            gender.text?.clear()
            birthCertificate.text?.clear()
            nin.text?.clear()
            phone.text?.clear()

            Toast.makeText(requireContext(),"Inserted New Farmer", Toast.LENGTH_LONG).show()
        }
    }
}