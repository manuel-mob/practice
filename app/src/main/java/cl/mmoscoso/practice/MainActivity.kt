package cl.mmoscoso.practice

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import cl.mmoscoso.practice.R
import cl.mmoscoso.practice.adapters.PatientAdapterRecyclerView
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
        val card4View = findViewById<View>(R.id.card4)
        val card5View = findViewById<View>(R.id.card5)
        val card6View = findViewById<View>(R.id.card6)
        val card7View = findViewById<View>(R.id.card7)
        val card8View = findViewById<View>(R.id.card8)
        val card9View = findViewById<View>(R.id.card9)
        val card10View = findViewById<View>(R.id.card10)
        val card11View = findViewById<View>(R.id.card11)
        val card12View = findViewById<View>(R.id.card12)

        card1View.findViewById<TextView>(R.id.cardTextViewTitle).setText(R.string.card_title_patients)
        card2View.findViewById<TextView>(R.id.cardTextViewTitle).setText(R.string.card_title_touch)
        card3View.findViewById<TextView>(R.id.cardTextViewTitle).setText(R.string.card_title_sensor)
        card4View.findViewById<TextView>(R.id.cardTextViewTitle).setText(R.string.card_title_practice)
        card5View.findViewById<TextView>(R.id.cardTextViewTitle).setText(R.string.card_title_mediaplayer)
        card6View.findViewById<TextView>(R.id.cardTextViewTitle).setText(R.string.card_title_customitem)
        card7View.findViewById<TextView>(R.id.cardTextViewTitle).setText(R.string.card_title_theme)
        card8View.findViewById<TextView>(R.id.cardTextViewTitle).setText(R.string.card_title_intent)
        card9View.findViewById<TextView>(R.id.cardTextViewTitle).setText(R.string.card_title_recyclerview)
        card10View.findViewById<TextView>(R.id.cardTextViewTitle).setText(R.string.card_title_lastlocation)
        card11View.findViewById<TextView>(R.id.cardTextViewTitle).setText(R.string.card_title_accelerometer_change_color)
        card12View.findViewById<TextView>(R.id.cardTextViewTitle).setText(R.string.card_title_environment)

        card1View.findViewById<TextView>(R.id.cardTextViewDescription).setText(R.string.card_desc_patients)
        card2View.findViewById<TextView>(R.id.cardTextViewDescription).setText(R.string.card_desc_touch)
        card3View.findViewById<TextView>(R.id.cardTextViewDescription).setText(R.string.card_desc_sensor)
        card4View.findViewById<TextView>(R.id.cardTextViewDescription).setText(R.string.card_desc_practice)
        card5View.findViewById<TextView>(R.id.cardTextViewDescription).setText(R.string.card_desc_mediaplayer)
        card6View.findViewById<TextView>(R.id.cardTextViewDescription).setText(R.string.card_desc_customitem)
        card7View.findViewById<TextView>(R.id.cardTextViewDescription).setText(R.string.card_desc_theme)
        card8View.findViewById<TextView>(R.id.cardTextViewDescription).setText(R.string.card_desc_intent)
        card9View.findViewById<TextView>(R.id.cardTextViewDescription).setText(R.string.card_desc_recyclerview)
        card10View.findViewById<TextView>(R.id.cardTextViewDescription).setText(R.string.card_desc_lastlocation)
        card11View.findViewById<TextView>(R.id.cardTextViewDescription).setText(R.string.card_desc_accelerometer_change_color)
        card12View.findViewById<TextView>(R.id.cardTextViewDescription).setText(R.string.card_desc_environment)

        card1View.findViewById<TextView>(R.id.cardButtonNavigate).setOnClickListener{ view ->
            goPatientApp(view)
        }
        card2View.findViewById<TextView>(R.id.cardButtonNavigate).setOnClickListener{ view ->
            goTouchEventApp(view)
        }
        card3View.findViewById<TextView>(R.id.cardButtonNavigate).setOnClickListener{ view ->
            goSensorApp(view)
        }
        card4View.findViewById<TextView>(R.id.cardButtonNavigate).setOnClickListener{ view ->
            goCreatePatientPractice(view)
        }
        card5View.findViewById<TextView>(R.id.cardButtonNavigate).setOnClickListener { view ->
            goMediaPlayerActivity(view)
        }
        card6View.findViewById<TextView>(R.id.cardButtonNavigate).setOnClickListener { view ->
            goCustomItemPatientActivity(view)
        }
        card7View.findViewById<TextView>(R.id.cardButtonNavigate).setOnClickListener { view ->
            goCustomTabs(view)
        }
        card8View.findViewById<TextView>(R.id.cardButtonNavigate).setOnClickListener { view ->
            goIntentOptions(view)
        }
        card9View.findViewById<TextView>(R.id.cardButtonNavigate).setOnClickListener { view ->
            goActivityRecyclerView(view)
        }

        card10View.findViewById<TextView>(R.id.cardButtonNavigate).setOnClickListener { view ->
            goLastLocationActivityView(view)
        }

        card11View.findViewById<TextView>(R.id.cardButtonNavigate).setOnClickListener { view ->
            goAccelerometerChangeColor(view)
        }
        card12View.findViewById<TextView>(R.id.cardButtonNavigate).setOnClickListener { view ->
            goEnvironmentActivity(view)
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

    fun goCreatePatientPractice(view: View) {
        val createActivity = Intent(this, CreatePatient::class.java)
        startActivity(createActivity)
    }

    fun goTabApp(view: View) {
        val inventory = Intent(this, Inventory::class.java)
        startActivity(inventory)

    }
    fun goMediaPlayerActivity(view: View) {
        val media = Intent(this, MediaActivity::class.java)
        startActivity(media)
    }

    fun goCustomItemPatientActivity(view: View) {
        val customItem = Intent(this, PatientListCustomItem::class.java)
        startActivity(customItem)
    }
    fun goCustomTabs(view: View) {
        val customItem = Intent(this, Inventory::class.java)
        startActivity(customItem)
    }

    fun goIntentOptions(view: View) {
        val customItem = Intent(this, IntentTestActivity::class.java)
        startActivity(customItem)
    }

    fun goActivityRecyclerView(view: View) {
        val recycleViewActivity = Intent(this, PatientListRecyclerViewActivity::class.java)
        startActivity(recycleViewActivity)
    }

    fun goLastLocationActivityView(view: View) {
        val  getLastLocation = Intent(this, LastLocationActivity::class.java)
        startActivity(getLastLocation)
    }

    fun goAccelerometerChangeColor(view: View) {
        val  changeColor = Intent(this, ColorChangerActivity::class.java)
        startActivity(changeColor)
    }
    fun goEnvironmentActivity(view: View) {
        val  environmentActivity = Intent(this, EnvironmentSensorsActivity::class.java)
        startActivity(environmentActivity)
    }

}