package cl.mmoscoso.practice

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import cl.mmoscoso.practice.R
import cl.mmoscoso.practice.entity.Patient

class PatientDetailActivity : AppCompatActivity() {

    private lateinit var textName: TextView
    private lateinit var textEmail: TextView
    private lateinit var textRoom: TextView
    private lateinit var textAge: TextView

    private lateinit var patient: Patient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_detail)

        textName = findViewById(R.id.textViewName);
        textEmail = findViewById(R.id.textViewEmail);
        textRoom = findViewById(R.id.textViewRoom);
        textAge = findViewById(R.id.textViewAge);

        if (Build.VERSION.SDK_INT >= 33){ // TIRAMISU
            patient = intent.getParcelableExtra("patient",Patient::class.java) ?: Patient("", "", "", 0, listOf())
        }else{
            patient = intent.getParcelableExtra("patient") ?: Patient("", "", "", 0, listOf())
        }



        textName.text = patient.name
        textEmail.text = patient.email
        textRoom.text = patient.room
        textAge.setText(patient.age.toString())

        textName.setOnClickListener(){
            Toast.makeText(this,patient.room,Toast.LENGTH_LONG).show()
        }

    }
}

