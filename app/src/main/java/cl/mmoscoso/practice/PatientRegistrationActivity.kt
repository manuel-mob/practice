package cl.mmoscoso.practice

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cl.mmoscoso.practice.entity.Patient

class PatientRegistrationActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextRoom: EditText
    private lateinit var editTextAge: EditText
    private lateinit var buttonSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_registration)

        // Initialize UI elements
        editTextName = findViewById(R.id.editTextName)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextRoom = findViewById(R.id.editTextRoom)
        editTextAge = findViewById(R.id.editTextAge)
        buttonSave = findViewById(R.id.buttonSave)

        buttonSave.setOnClickListener {
            // Retrieve user input
            val name = editTextName.text.toString()
            val email = editTextEmail.text.toString()
            val room = editTextRoom.text.toString()
            val age = editTextAge.text.toString().toIntOrNull() ?: 0 // Default to 0 if parsing fails

            // Create a new Patient object
            if (name.length < 1 || email.length < 1 || room.length < 1) {
                Toast.makeText(this,"You must include some information",Toast.LENGTH_LONG).show();
            }
            else {
                val patient = Patient(name, email, room, age, emptyList())
                val resultIntent = Intent()
                resultIntent.putExtra("new", patient)
                setResult(RESULT_OK, resultIntent)
                finish() // Close the registration activity.
            }
        }
    }
}
