package cl.mmoscoso.practice

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.card_container)
        setSupportActionBar(findViewById(R.id.toolbar))

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
        val card13View = findViewById<View>(R.id.card13)
        val card14View = findViewById<View>(R.id.card14)
        val card15View = findViewById<View>(R.id.card15)

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
        card13View.findViewById<TextView>(R.id.cardTextViewTitle).setText(R.string.card_title_changer)
        card14View.findViewById<TextView>(R.id.cardTextViewTitle).setText(R.string.card_title_room)
        card15View.findViewById<TextView>(R.id.cardTextViewTitle).setText(R.string.card_title_api)

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
        card13View.findViewById<TextView>(R.id.cardTextViewDescription).setText(R.string.card_desc_changer)
        card14View.findViewById<TextView>(R.id.cardTextViewDescription).setText(R.string.card_desc_room)
        card15View.findViewById<TextView>(R.id.cardTextViewDescription).setText(R.string.card_desc_api)

        card1View.findViewById<TextView>(R.id.cardButtonNavigate).setOnClickListener{
            goPatientApp()
        }
        card2View.findViewById<TextView>(R.id.cardButtonNavigate).setOnClickListener{
            goTouchEventApp()
        }
        card3View.findViewById<TextView>(R.id.cardButtonNavigate).setOnClickListener{
            goSensorApp()
        }
        card4View.findViewById<TextView>(R.id.cardButtonNavigate).setOnClickListener{
            goCreatePatientPractice()
        }
        card5View.findViewById<TextView>(R.id.cardButtonNavigate).setOnClickListener {
            goMediaPlayerActivity()
        }
        card6View.findViewById<TextView>(R.id.cardButtonNavigate).setOnClickListener {
            goCustomItemPatientActivity()
        }
        card7View.findViewById<TextView>(R.id.cardButtonNavigate).setOnClickListener {
            goCustomTabs()
        }
        card8View.findViewById<TextView>(R.id.cardButtonNavigate).setOnClickListener {
            goIntentOptions()
        }
        card9View.findViewById<TextView>(R.id.cardButtonNavigate).setOnClickListener {
            goActivityRecyclerView()
        }

        card10View.findViewById<TextView>(R.id.cardButtonNavigate).setOnClickListener {
            goLastLocationActivityView()
        }

        card11View.findViewById<TextView>(R.id.cardButtonNavigate).setOnClickListener {
            goAccelerometerChangeColor()
        }
        card12View.findViewById<TextView>(R.id.cardButtonNavigate).setOnClickListener {
            goEnvironmentActivity()
        }
        card13View.findViewById<TextView>(R.id.cardButtonNavigate).setOnClickListener {
            goChangerActivity()
        }
        card14View.findViewById<TextView>(R.id.cardButtonNavigate).setOnClickListener {
            goUserRoomExampleActivity()
        }
        card15View.findViewById<TextView>(R.id.cardButtonNavigate).setOnClickListener {
            goAPIRequestActivity()
        }



    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.go_preference -> {
                // Handle the "Settings" menu item click
                val preference = Intent(this, SettingsActivity::class.java)
                startActivity(preference)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun goPatientApp() {
        val intentPatientList = Intent(this, PatientListActivity::class.java)
        startActivity(intentPatientList)
    }
    fun goTouchEventApp() {
        val touchApp = Intent(this, TouchEventActivity::class.java)
        startActivity(touchApp)
    }
    fun goSensorApp() {
        val sensorApp = Intent(this, SensorDetectionActivity::class.java)
        startActivity(sensorApp)
    }

    fun goCreatePatientPractice() {
        val createActivity = Intent(this, CreatePatient::class.java)
        startActivity(createActivity)
    }

    fun goTabApp() {
        val inventory = Intent(this, Inventory::class.java)
        startActivity(inventory)

    }
    fun goMediaPlayerActivity() {
        val media = Intent(this, MediaActivity::class.java)
        startActivity(media)
    }

    fun goCustomItemPatientActivity() {
        val customItem = Intent(this, PatientListCustomItem::class.java)
        startActivity(customItem)
    }
    fun goCustomTabs() {
        val customItem = Intent(this, Inventory::class.java)
        startActivity(customItem)
    }

    fun goIntentOptions() {
        val customItem = Intent(this, IntentTestActivity::class.java)
        startActivity(customItem)
    }

    fun goActivityRecyclerView() {
        val recycleViewActivity = Intent(this, PatientListRecyclerViewActivity::class.java)
        startActivity(recycleViewActivity)
    }

    fun goLastLocationActivityView() {
        val  getLastLocation = Intent(this, LastLocationActivity::class.java)
        startActivity(getLastLocation)
    }

    fun goAccelerometerChangeColor() {
        val  changeColor = Intent(this, ColorChangerActivity::class.java)
        startActivity(changeColor)
    }
    fun goEnvironmentActivity() {
        val  environmentActivity = Intent(this, EnvironmentSensorsActivity::class.java)
        startActivity(environmentActivity)
    }
    fun goChangerActivity() {
        val  changerActivity = Intent(this, ChangerActivity::class.java)
        startActivity(changerActivity)
    }

    fun goUserRoomExampleActivity() {
        val  userRoomExampleActivity = Intent(this, UserRoomExampleActivity::class.java)
        startActivity(userRoomExampleActivity)
    }
    fun goAPIRequestActivity(){
        val  apiRestActivitty = Intent(this, APIRestActivity::class.java)
        startActivity(apiRestActivitty)
    }
}