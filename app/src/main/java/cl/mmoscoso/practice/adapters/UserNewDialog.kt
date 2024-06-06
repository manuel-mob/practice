package cl.mmoscoso.practice.adapters

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import androidx.room.Room
import cl.mmoscoso.practice.R
import cl.mmoscoso.practice.UserRoomExampleActivity
import cl.mmoscoso.practice.database.AppDatabase
import cl.mmoscoso.practice.entity.User

class UserNewDialog(
    context: Context,
    idUser : Int,
    act : UserRoomExampleActivity
) : Dialog(context) {

    private lateinit var firstname : EditText
    private lateinit var lastname : EditText
    private lateinit var email : EditText
    private lateinit var address : EditText
    private lateinit var db : AppDatabase
    private var id : Int = idUser
    private var act : UserRoomExampleActivity = act

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_user_new)

        firstname = findViewById(R.id.editTextFirstname)
        lastname = findViewById(R.id.editTextLastname)
        email = findViewById(R.id.editTextEmail)
        address = findViewById(R.id.editTextAddress)

        db = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries().build()


        val buttonAddAndGoBack : Button = findViewById(R.id.buttonAddAndGoBack)
        // Set a click listener for the "Go Back" button to dismiss the dialog
        buttonAddAndGoBack.setOnClickListener {
            //Add user to database


            db.userDao().insertAll(
                User(id,firstname.text.toString(),lastname.text.toString(),email.text.toString(),address.text.toString())
            )
            act.refreshFromDatabase()
            dismiss()
        }
    }


}
