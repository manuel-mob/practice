package cl.mmoscoso.practice

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import cl.mmoscoso.practice.R
import cl.mmoscoso.practice.entity.Patient


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.card_container)

        val patien1 = Patient("Patient 1", "patient1@example.com", "Room 101", 25, listOf("Fever", "Cough"))

        // Access the individual card views by their unique IDs
        val card1View = findViewById<View>(R.id.card1)
        val card2View = findViewById<View>(R.id.card2)
        val card3View = findViewById<View>(R.id.card3)
        card1View.findViewById<TextView>(R.id.cardTextViewTitle).setText(R.string.card_title_patients)
        card2View.findViewById<TextView>(R.id.cardTextViewTitle).setText(R.string.card_title_touch)
        card3View.findViewById<TextView>(R.id.cardTextViewTitle).setText(R.string.card_title_sensor)
        card1View.findViewById<TextView>(R.id.cardTextViewDescription).setText(R.string.card_desc_patients)
        card2View.findViewById<TextView>(R.id.cardTextViewDescription).setText(R.string.card_desc_touch)
        card3View.findViewById<TextView>(R.id.cardTextViewDescription).setText(R.string.card_desc_sensor)
        card1View.findViewById<TextView>(R.id.cardButtonNavigate).setOnClickListener{ view ->
            goPatientApp(view)
        }
        card2View.findViewById<TextView>(R.id.cardButtonNavigate).setOnClickListener{ view ->
            goTouchEventApp(view)
        }
        card3View.findViewById<TextView>(R.id.cardButtonNavigate).setOnClickListener{ view ->
            goSensorApp(view)
        }

    }

    fun goPatientApp(view: View) {
        val intentPatientList = Intent(this, PatientListActivity::class.java)
        startActivity(intentPatientList)
    }
    fun goTouchEventApp(view: View) {
        val touchApp = Intent(this, TouchEventActivity::class.java)
        startActivity(touchApp)
    }
    fun goSensorApp(view: View) {
        val sensorApp = Intent(this, SensorDetectionActivity::class.java)
        startActivity(sensorApp)
    }

    fun goTabApp(view: View) {
        val inventory = Intent(this, Inventory::class.java)
        startActivity(inventory)
    }
}