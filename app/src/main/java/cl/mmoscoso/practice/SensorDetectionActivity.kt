package cl.mmoscoso.practice

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SensorDetectionActivity : AppCompatActivity() {

    private lateinit var sensorManager: SensorManager
    private lateinit var sensorListTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensor_detection)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorListTextView = findViewById(R.id.sensorListTextView)

        // Get the list of available sensors
        val sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL)

        // Display the list of sensors
        val sensorInfoList = StringBuilder()
        for (sensor in sensorList) {
            sensorInfoList.append("${sensor.name} (${sensor.typeToString()})\n")
        }
        sensorListTextView.text = sensorInfoList.toString()
    }

    private fun Sensor.typeToString(): String {
        return when (this.type) {
            Sensor.TYPE_ACCELEROMETER -> "Accelerometer"
            Sensor.TYPE_GYROSCOPE -> "Gyroscope"
            Sensor.TYPE_PROXIMITY -> "Proximity"
            Sensor.TYPE_MAGNETIC_FIELD -> "Magnetometer (Compass)"
            Sensor.TYPE_LIGHT -> "Light"
            // Add more sensor types as needed
            else -> "Unknown Sensor"
        }
    }
}