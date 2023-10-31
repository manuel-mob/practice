package cl.mmoscoso.practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cl.mmoscoso.practice.adapters.PatientAdapterRecyclerView
import cl.mmoscoso.practice.entity.Patient

class PatientListRecyclerViewActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PatientAdapterRecyclerView
    private lateinit var patients : MutableList<Patient>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.patient_list_recycleview)

        // Initialize the list of patients (you can populate this list as needed)
        patients = mutableListOf(
            Patient("Patient 1", "patient1@example.com", "Room 101", 25, emptyList()),
            Patient("Patient 2", "patient2@example.com", "Room 102", 30, emptyList()),
            Patient("Patient 3", "patient2@example.com", "Room 102", 30, emptyList()),
            Patient("Patient 4", "patient2@example.com", "Room 102", 30, emptyList()),
            Patient("Patient 5", "patient2@example.com", "Room 102", 30, emptyList()),
            // Add more patients as needed
        )

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PatientAdapterRecyclerView(patients)
        recyclerView.adapter = adapter
    }
}