package cl.mmoscoso.practice

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class EnvironmentSensorsActivity : AppCompatActivity() , SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private lateinit var txtAmbientTemperature: TextView
    private lateinit var txtLight: TextView
    private lateinit var txtPressure : TextView
    private lateinit var txtRelativeHumidity : TextView

    private var ambientTemperatureSensor : Sensor? = null
    private var lightSensor : Sensor? = null
    private var pressureSensor : Sensor? = null
    private var relativeHumiditySensor : Sensor? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_environment_sensors)

        this.sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager


        /*ambientTemperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE)
        relativeHumiditySensor = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY)
*/
        val sensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
        if (sensor != null) {
            ambientTemperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
        }
        val sensor1 : Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        if (sensor != null) {
            ambientTemperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        }
        val sensor2: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE)
        if (sensor != null) {
            ambientTemperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE)
        }
        val sensor3: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY)
        if (sensor != null) {
            ambientTemperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY)
        }

            txtAmbientTemperature = findViewById(R.id.valueAmbiente)
        txtLight  = findViewById(R.id.valueLight)
        txtPressure  = findViewById(R.id.valuePressureSensor)
        txtRelativeHumidity  = findViewById(R.id.valueRelativeHumiditySensor)


        registerSensors()
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            when (event.sensor.type) {
                Sensor.TYPE_AMBIENT_TEMPERATURE -> txtAmbientTemperature.text = event.values[0].toString()
                Sensor.TYPE_LIGHT -> txtLight.text = event.values[0].toString()
                Sensor.TYPE_PRESSURE -> txtPressure.text = event.values[0].toString()
                Sensor.TYPE_RELATIVE_HUMIDITY -> txtRelativeHumidity.text = event.values[0].toString()
            }
        }
    }

    fun registerSensors(){
        sensorManager.registerListener(this, ambientTemperatureSensor, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this, pressureSensor, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this, relativeHumiditySensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Not used in this example
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onResume() {
        super.onResume()
        this.registerSensors()
    }
}