package cl.mmoscoso.practice

import android.app.Activity
import android.content.ClipData
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import cl.mmoscoso.practice.adapters.PatientListAdapterCustomButtonItem
import cl.mmoscoso.practice.entity.Patient

class PatientListCustomItem : AppCompatActivity() {

    private lateinit var listViewPatientsCustomItem: ListView
    private var listOption: Boolean = true
    private var detailOption: Boolean = false
    private lateinit var patients: MutableList<Patient>
    private lateinit var adapterItems: PatientListAdapterCustomButtonItem
    var EDIT_PATIENT_REQUEST_CODE = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_list_custom_item)

        // Initialize UI elements
        listViewPatientsCustomItem = findViewById(R.id.listViewPatientsItemCustom)

        // Create a sample list of patients (you should replace this with your data source)
        patients = mutableListOf(
            Patient("Patient 1", "patient1@example.com", "Room 101", 25, emptyList()),
            Patient("Patient 2", "patient2@example.com", "Room 102", 30, emptyList()),
            Patient("Patient 3", "patient2@example.com", "Room 102", 30, emptyList()),
            Patient("Patient 4", "patient2@example.com", "Room 102", 30, emptyList()),
            Patient("Patient 5", "patient2@example.com", "Room 102", 30, emptyList()),
            // Add more patients as needed
        )

        adapterItems = PatientListAdapterCustomButtonItem(this, R.layout.list_item_custom_patient, patients)
        listViewPatientsCustomItem.adapter = adapterItems

        adapterItems.onEditClickListener = { patient,position ->
            // Handle the edit action for the given patient
            // Start the PatientEditActivity to edit the patient
            val intent = Intent(this, PatientEditActivity::class.java)
            intent.putExtra("patient", patient)
            intent.putExtra("position", position)
            startActivityForResult(intent, EDIT_PATIENT_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == EDIT_PATIENT_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val editedPatient = data?.getParcelableExtra<Patient>("patient")
            val position = data!!.getIntExtra("position",-1)

            if (position != -1) {
                if (editedPatient != null) {
                    patients.set(position,editedPatient)
                }
            }
            adapterItems.notifyDataSetChanged()
        }
    }

}
