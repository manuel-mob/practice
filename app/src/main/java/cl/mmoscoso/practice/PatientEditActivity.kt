package cl.mmoscoso.practice

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import cl.mmoscoso.practice.entity.Patient

class PatientEditActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextRoom: EditText
    private lateinit var editTextAge: EditText
    private lateinit var buttonSaveChanges: Button

    private lateinit var patient: Patient // The patient being edited

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_edit)

        // Initialize UI elements
        editTextName = findViewById(R.id.editTextName)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextRoom = findViewById(R.id.editTextRoom)
        editTextAge = findViewById(R.id.editTextAge)
        buttonSaveChanges = findViewById(R.id.buttonSave)

        // Retrieve the patient object passed from the previous activity
        patient = intent.getParcelableExtra("patient")!!

        // Populate EditText fields with the patient's current information
        editTextName.setText(patient.name)
        editTextEmail.setText(patient.email)
        editTextRoom.setText(patient.room)
        editTextAge.setText(patient.age.toString())

        buttonSaveChanges.setOnClickListener {
            // Retrieve and update user input
            val updatedName = editTextName.text.toString()
            val updatedEmail = editTextEmail.text.toString()
            val updatedRoom = editTextRoom.text.toString()
            val updatedAge = editTextAge.text.toString().toIntOrNull() ?: 0 // Default to 0 if parsing fails

            // Update the patient object with the edited information
            patient = patient.copy(
                name = updatedName,
                email = updatedEmail,
                room = updatedRoom,
                age = updatedAge
            )

            // Pass the updated patient object back to the previous activity
            val resultIntent = Intent()
            resultIntent.putExtra("patient", patient)
            resultIntent.putExtra("position",intent.getIntExtra("position",0))
            setResult(RESULT_OK, resultIntent)
            finish() // Close the editing activity and return to the previous activity
        }
    }
}
