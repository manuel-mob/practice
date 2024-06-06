package cl.mmoscoso.practice

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import cl.mmoscoso.practice.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class LastLocationActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var latitudeTextView: TextView
    private lateinit var longitudeTextView: TextView
    private val LOCATION_PERMISSION_REQUEST = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_last_location)

        latitudeTextView = findViewById(R.id.latTextView)
        longitudeTextView = findViewById(R.id.lonTextView)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        if (checkLocationPermission()) {
            fetchLastLocation()
        } else {
            requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST)
        }
    }

    private fun checkLocationPermission(): Boolean {
        return (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
    }

    private fun fetchLastLocation() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    latitudeTextView.text = "Latitude: ${location.latitude}"
                    longitudeTextView.text = "Longitude: ${location.longitude}"
                } else {
                    latitudeTextView.text = "Location not available"
                    longitudeTextView.text = "Location not available"
                }
            }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == LOCATION_PERMISSION_REQUEST) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                fetchLastLocation()
            } else {
                // Handle the case when the user denies permission
                latitudeTextView.text = "Location permission denied"
                longitudeTextView.text = "Location permission denied"
            }
        }
    }
}