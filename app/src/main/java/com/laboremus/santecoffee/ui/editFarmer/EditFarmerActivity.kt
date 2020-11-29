package com.laboremus.santecoffee.ui.editFarmer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.laboremus.santecoffee.MainActivity
import com.laboremus.santecoffee.R
import com.laboremus.santecoffee.db.Audit
import com.laboremus.santecoffee.db.Farmer
import com.laboremus.santecoffee.ui.audits.AuditViewModel
import com.laboremus.santecoffee.util.DISPLAY_NAME
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_edit_farmer.*
import kotlinx.android.synthetic.main.fragment_add_farmer.*
import java.util.*

@AndroidEntryPoint
class EditFarmerActivity : AppCompatActivity() {

    private val editFarmerViewModel: EditFarmerViewModel by viewModels()
    private val auditFarmerViewModel: AuditViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_farmer)
        val farmer_object = intent.getParcelableExtra<Farmer>("farmer_parcel")
        val farmer_id = intent.getIntExtra("id", 0)
        edit_name_textView.text = farmer_object?.name
        edit_number_editText.setText(farmer_object?.phoneNumber)
        edit_bc_url_editText.setText(farmer_object?.birthCertificateUrl)

        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)

        submit_edit_button.setOnClickListener {
            if (edit_number_editText.text.toString().isEmpty() ||
                    edit_bc_url_editText.text.toString().isEmpty()) {
                Toast.makeText(this, "Missing Fields", Toast.LENGTH_LONG).show()
            }
            else{
            farmer_object?.Id = farmer_id
            farmer_object?.phoneNumber = edit_number_editText.text.toString()
            farmer_object?.birthCertificateUrl = edit_bc_url_editText.text.toString()
            updateFarmer(
                    farmer_object!!
            )
            Toast.makeText(
                    this,
                    "Successfully Edited Farmer ${farmer_object.name} " +
                            "with phone number ${farmer_object.phoneNumber}" +
                            "and ID ${farmer_object.Id}",
                    Toast.LENGTH_LONG).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun updateFarmer(farmer: Farmer) {
        editFarmerViewModel.updateFarmer(farmer)
        val audit_object = Audit(DISPLAY_NAME, Calendar.getInstance().timeInMillis)
        audit_object.farmer_Id =farmer.Id
        auditFarmerViewModel.insertAudit(audit_object)
    }
}