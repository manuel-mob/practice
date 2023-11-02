package cl.mmoscoso.practice

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.Random

class ChangerActivity : AppCompatActivity() {
    private lateinit var text : TextView
    private lateinit var button : Button
    private lateinit var list : ArrayList<String>
    private var timeInterval = 3000L // Initial time interval, set to 3 seconds (3000 milliseconds)
    private val handler = Handler(Looper.getMainLooper())
    private var count  = 0
    private lateinit var updateText: Runnable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_changer)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        text = findViewById(R.id.textViewChanging)
        button = findViewById(R.id.buttonSpeedUp)
        changeTextButton()


        list = ArrayList()

        list.add("ROJO")
        list.add("VERDE")
        list.add("AMARILLO")
        list.add("AZUL")


        button.setOnClickListener {
            decreaseTimeDelay()
            changeButtonLocation()
        }

        updateText = updateTextRunnable

    }

    private fun changeTextButton () {
        val textButton : CharSequence? = button.text.toString() + "(" + count.toString() + " ) "
        button.text = textButton
    }

    private val updateTextRunnable = object : Runnable {
        override fun run() {
            text.text = changeInstruction() + " (" + timeInterval.toString() + " )"
            handler.postDelayed(updateText, timeInterval)
        }
    }

    private fun changeInstruction() : String? {
        val random = Random()
        val randomIndex = random.nextInt(list.size)
        return list[randomIndex]
    }

    private fun decreaseTimeDelay() {
        count += 1
        changeTextButton()
        if (count == 3) {
            handler.removeCallbacks(updateText)
            return
        }
        timeInterval -= 500L
        handler.removeCallbacks(updateText) // Remove previous callbacks
        handler.postDelayed(updateText, timeInterval)
    }

    private fun changeButtonLocation(){
        val params = button.layoutParams as ConstraintLayout.LayoutParams
        val constraintLayout = button.parent as ConstraintLayout

        // Get the width and height of ConstraintLayout
        val layoutWidth = constraintLayout.width - button.width
        val layoutHeight = constraintLayout.height - button.height

        // Set new X and Y coordinates for the button
        val newX = (0..layoutWidth).random().toFloat()
        val newY = (0..layoutHeight).random().toFloat()

        // Update button's position
        params.leftMargin = newX.toInt()
        params.topMargin = newY.toInt()
        button.layoutParams = params
    }
}