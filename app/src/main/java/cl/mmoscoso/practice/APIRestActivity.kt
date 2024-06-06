package cl.mmoscoso.practice

import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cl.mmoscoso.practice.background.ApiCallback
import cl.mmoscoso.practice.background.ApiTask
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject



class APIRestActivity : AppCompatActivity(),ApiCallback {
    private lateinit var listDataFromJson : ListView
    private lateinit var getRequestButton : Button
    private lateinit var adapter : ArrayAdapter<String>
    private lateinit var listData : MutableList<String>
    //private  var URL : String = "https://reqres.in/api/users"
    private  var URL : String = "https://apis.digital.gob.cl/fl/feriados"
    private var TAG = "APIRestActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apirest)

        //Network state
        val connectivityManager = getSystemService(ConnectivityManager::class.java)
        connectivityManager.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network : Network) {
                Log.e(TAG, "The default network is now: " + network)
            }

            override fun onLost(network : Network) {
                Log.e(TAG, "The application no longer has a default network. The last default network was " + network)
            }

            override fun onCapabilitiesChanged(network : Network, networkCapabilities : NetworkCapabilities) {
                Log.e(TAG, "The default network changed capabilities: " + networkCapabilities)
            }

            override fun onLinkPropertiesChanged(network : Network, linkProperties : LinkProperties) {
                Log.e(TAG, "The default network changed link properties: " + linkProperties)
            }
        })


        listDataFromJson = findViewById(R.id.listViewDataFromJson)
        getRequestButton = findViewById(R.id.getRequest)

        listData = mutableListOf(
            getString(R.string.api_instruction)
        )

        adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1, // Built-in layout for simple items
            listData
        )

        listDataFromJson.adapter = adapter

        getRequestButton.setOnClickListener{
            val apiRequestTask = ApiTask(this)
            apiRequestTask.execute(URL)
        }
    }

    override fun onRequestComplete(result: String) {
        //Update GUI
        Toast.makeText(this,result,Toast.LENGTH_LONG).show()
        //listData = processingData(result)
        listData = processingHolidaysData(result)
        Log.i("APIRestActivity",listData.toString())
        adapter.clear()
        adapter.addAll(listData)
        adapter.notifyDataSetChanged()

    }

    fun processingData(result: String) : MutableList<String> {
        var list : MutableList<String> = mutableListOf()
        try {
            // Parse the JSON string into a JSONObject
            val jsonObject = JSONObject(result)

            // Access values from the JSON object
            val page = jsonObject.getInt("page")
            val perPage = jsonObject.getInt("per_page")
            val total = jsonObject.getInt("total")
            val totalPages = jsonObject.getInt("total_pages")



            // Access the "data" array
            val dataArray = jsonObject.getJSONArray("data")

            // Iterate through the array and access individual objects
            for (i in 0 until dataArray.length()) {
                val dataObject = dataArray.getJSONObject(i)
                /*
                val id = dataObject.getInt("id")
                val email = dataObject.getString("email")

                val lastName = dataObject.getString("last_name")
                val avatar = dataObject.getString("avatar")
                */
                val firstName = dataObject.getString("first_name")
                Log.i("APIRestActivity",firstName.toString())
                list.add(firstName)
            }

            // Access the "support" object
            /*
            val supportObject = jsonObject.getJSONObject("support")
            val supportUrl = supportObject.getString("url")
            val supportText = supportObject.getString("text")*/

        } catch (e: JSONException) {
            e.printStackTrace()
            list.add(getString(R.string.api_error))
        }
        return list.toMutableList()
    }

    fun processingHolidaysData(result: String) : MutableList<String> {
        var list : MutableList<String> = mutableListOf()
        try {
            // Parse the JSON string into a JSONObject
            val jsonArray = JSONArray(result)
            // Iterate through the array and access individual objects
            for (i in 0 until jsonArray.length()) {
                val dataObject = jsonArray.getJSONObject(i)

                val firstName = dataObject.getString("nombre")
                Log.i("APIRestActivity",firstName.toString())
                list.add(firstName)
            }

            // Access the "support" object
            /*
            val supportObject = jsonObject.getJSONObject("support")
            val supportUrl = supportObject.getString("url")
            val supportText = supportObject.getString("text")*/

        } catch (e: JSONException) {
            e.printStackTrace()
            list.add(getString(R.string.api_error))
        }
        return list.toMutableList()
    }
}