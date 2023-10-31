package cl.mmoscoso.practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import cl.mmoscoso.practice.R

class TouchEventActivity : AppCompatActivity() {

    private lateinit var gestureDetector: GestureDetector
    private lateinit var textViewTouchEvent: TextView
    private lateinit var listViewTouchEventHistory: ListView
    private lateinit var touchEventHistory: ArrayList<String>
    private lateinit var historyAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_touch_event)
        gestureDetector = GestureDetector(this, GestureListener())

        textViewTouchEvent = findViewById(R.id.textViewTouchEvent)
        listViewTouchEventHistory = findViewById(R.id.listViewTouchEventHistory)

        //Creating the list
        touchEventHistory = ArrayList()
        historyAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, touchEventHistory)
        listViewTouchEventHistory.adapter = historyAdapter



    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        gestureDetector.onTouchEvent(event)
        return super.onTouchEvent(event)
    }

    inner class GestureListener : GestureDetector.SimpleOnGestureListener() {
        /*override fun onDown(e: MotionEvent): Boolean {
            showToast("onDown")
            addToList("onDown")
            return true
        }

        override fun onSingleTapUp(e: MotionEvent): Boolean {
            showToast("onSingleTapUp")
            addToList("onSingleTapUp")
            return true
        }

        override fun onLongPress(e: MotionEvent) {
            showToast("onLongPress")
            addToList("onLongPress")

        }
        */
        override fun onFling(
            e1: MotionEvent, e2: MotionEvent,
            velocityX: Float, velocityY: Float
        ): Boolean {
            showToast("onFling")
            addToList("onFling")
            return true
        }

        override fun onDoubleTap(e: MotionEvent): Boolean {
            showToast("onDoubleTap")
            addToList("onDoubleTap")
            return super.onDoubleTap(e)
        }
/*

        override fun onScroll(
            e1: MotionEvent, e2: MotionEvent,
            distanceX: Float, distanceY: Float
        ): Boolean {
            showToast("onScroll")
            addToList("onScroll")
            return true
        }*/
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    private fun addToList(action: String){
        val historyEntry = "Action: $action"
        textViewTouchEvent.text = action
        touchEventHistory.add(0, historyEntry)
        historyAdapter.notifyDataSetChanged()
    }

}
