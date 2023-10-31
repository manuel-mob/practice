package cl.mmoscoso.practice

import android.content.Intent
import android.net.Uri
import android.net.Uri.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class IntentTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_test)

        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = parse("https://www.mobiquos.cl")
        startActivity(intent)

//        val sendIntent = Intent().apply {
//            action = Intent.ACTION_SEND
//            type = "text/plain"
//            putExtra(Intent.EXTRA_TEXT, "This is the text you want to share.")
//        }
//
//        startActivity(Intent.createChooser(sendIntent, "Share text via..."))
//
//        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
//            data = Uri.parse("mailto:recipient@example.com")
//            putExtra(Intent.EXTRA_SUBJECT, "Email Subject")
//            putExtra(Intent.EXTRA_TEXT, "Email body text goes here.")
//        }
//
//        if (emailIntent.resolveActivity(packageManager) != null) {
//            startActivity(emailIntent)
//        } else {
//            // Handle the case where no email app is available
//            Toast.makeText(this, "No email app found.", Toast.LENGTH_SHORT).show()
//        }

    }
}