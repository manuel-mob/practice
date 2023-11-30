package cl.mmoscoso.practice

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import cl.mmoscoso.practice.adapters.PatientDetailDialog
import cl.mmoscoso.practice.adapters.PatientListAdapter
import cl.mmoscoso.practice.entity.Patient

class PatientListActivity : AppCompatActivity() {

    private lateinit var listViewPatients: ListView
    private var listOption: Boolean = true
    private var detailOption: Boolean = false
    private lateinit var patients: MutableList<Patient>
    private lateinit var adapterItems: PatientListAdapter
    private lateinit var adapter : ArrayAdapter<Patient>
    private lateinit var buttonChange : Button
    companion object {
        const val REQUEST_REGISTER = 1 // You can use any unique code
        const val REQUEST_EDITER = 2 // You can use any unique code
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_list)


        // Initialize UI elements
        listViewPatients = findViewById(R.id.listViewPatients)

        //Button Change
        buttonChange = findViewById(R.id.buttonChangeApapter)

        buttonChange.setOnClickListener {
            changeAdapter()
        }

        // Create a sample list of patients (you should replace this with your data source)
        patients = mutableListOf(
            Patient("Patient 1", "patient1@example.com", "Room 101", 25, emptyList()),
            Patient("Patient 2", "patient2@example.com", "Room 102", 30, emptyList()),
            Patient("Patient 3", "patient2@example.com", "Room 102", 30, emptyList()),
            Patient("Patient 4", "patient2@example.com", "Room 102", 30, emptyList()),
            Patient("Patient 5", "patient2@example.com", "Room 102", 30, emptyList()),
            // Add more patients as needed
        )

        // Create an ArrayAdapter to populate the ListView
//        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, patients.map { it.email })
        adapter = ArrayAdapter<Patient>(this, android.R.layout.simple_list_item_1, patients)

        listViewPatients.adapter = adapter



        // Handle item clicks to view patient details
        listViewPatients.setOnItemClickListener { _, _, position, _ ->
            val selectedPatient = patients[position]
            listOption = !listOption
            if (detailOption) {
                showPatientDetailDialog(selectedPatient)
            }
            else {
                val intent = Intent(this, PatientDetailActivity::class.java)
                intent.putExtra("patient", selectedPatient)

                startActivity(intent)
            }
        }
        registerForContextMenu(listViewPatients)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.patient_list_menu, menu)
    }
    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.action_show -> {
                //Handle the "Show" option
                val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
                val position = info.position
                showPatientDetailDialog(patients.get(position))
                true
            }

            R.id.action_edit -> {
                // Handle the "Edit" option
                val intent = Intent(this, PatientEditActivity::class.java)
                val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
                val position = info.position
                intent.putExtra("patient",patients.get(position))
                intent.putExtra("position",position)
                startActivityForResult(intent, REQUEST_EDITER)
                true
            }
            R.id.action_delete -> {
                // Handle the "Delete" option
                // Show the confirmation dialog when "Delete" is selected
                val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
                val position = info.position
                showDeleteConfirmationDialog(position)
                true
            }
            // Add cases for other options as needed
            else -> {
                super.onContextItemSelected(item)
            }
        }
    }

    fun changeAdapter() {
        if (listOption) {
            adapterItems = PatientListAdapter(this, R.layout.list_item_patient, patients)
            listViewPatients.adapter = adapterItems
        } else {
            // Perform actions when listOption is false
            //adapter = ArrayAdapter<Patient>(this, android.R.layout.simple_list_item_1, patients.map { it.name })
            adapter = ArrayAdapter<Patient>(this, android.R.layout.simple_list_item_1, patients)
            listViewPatients.adapter = adapter
        }
        listOption = !listOption
    }

    fun goCreatePatient() {
        val intent = Intent(this, PatientRegistrationActivity::class.java)
        startActivityForResult(intent, REQUEST_REGISTER)
    }
    fun goCreatePatient(view : View) {
        val intent = Intent(this, PatientRegistrationActivity::class.java)
        startActivityForResult(intent, REQUEST_REGISTER)
    }

    private fun showPatientDetailDialog(patient: Patient) {
        val dialog = PatientDetailDialog(this, patient)
        dialog.show()
    }

    private fun showDeleteConfirmationDialog(itemPosition: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(R.string.message_delete_patient)
        builder.setPositiveButton(R.string.btn_delete) { dialog, itemPosition ->
            // Handle the delete action here
            deleteItem(itemPosition)
        }
        builder.setNegativeButton(R.string.btn_cancel) { dialog, _ ->
            dialog.dismiss()
        }
        builder.create().show()
    }

    private fun deleteItem(itemPosition: Int) {
        patients.removeAt(itemPosition)
        adapterItems.notifyDataSetChanged()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_REGISTER && resultCode == RESULT_OK) {
            val newPatient = data?.getParcelableExtra<Patient>("new")
            if (newPatient != null) {
                patients.add(newPatient)
                if (listOption) {

                      }
                adapterItems.notifyDataSetChanged()
            }
        }
        if (requestCode == REQUEST_EDITER && resultCode == RESULT_OK) {
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
