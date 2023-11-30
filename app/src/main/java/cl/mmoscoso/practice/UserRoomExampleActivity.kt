package cl.mmoscoso.practice

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import cl.mmoscoso.practice.adapters.UserNewDialog
import cl.mmoscoso.practice.database.AppDatabase
import cl.mmoscoso.practice.entity.Product
import cl.mmoscoso.practice.entity.User
import cl.mmoscoso.practice.entity.UserWithProducts
import com.google.android.material.floatingactionbutton.FloatingActionButton



class UserRoomExampleActivity : AppCompatActivity() {
    private lateinit var db : AppDatabase
    private lateinit var listUsers : MutableList<User>
    private lateinit var listViewUsers : ListView
    private lateinit var adapter : ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_room_example)
        // Initialize UI elements
        listViewUsers = findViewById(R.id.listViewUsers)


        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

        val list : List<User> = db.userDao().getAll()
        listUsers = list.toMutableList()
        //adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list.map { it.firstName })
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listUsers.map { it.firstName })
        Toast.makeText(this,"Hi: "+listUsers.size.toString(),Toast.LENGTH_LONG).show()
        listViewUsers.adapter = adapter
        list.size

        val fab : FloatingActionButton = findViewById(R.id.idFabUser)

        fab.setOnClickListener{

            //Get the max ID
            var tempMax = 0;
            list.forEach { data ->
                if (data.uid > tempMax ) {
                    tempMax = data.uid
                }
            }
            val dialog = UserNewDialog(this,tempMax+1,this)
            dialog.show()
        }


        registerForContextMenu(listViewUsers)
    }



    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.patient_list_menu, menu)
    }
    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.action_show -> {
                //Handle the "Show" option
                val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
                val position = info.position
                //showPatientDetailDialog(patients.get(position))
                true
            }

            R.id.action_edit -> {
                // Handle the "Edit" option
                val intent = Intent(this, PatientEditActivity::class.java)
                val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
                val position = info.position
                //intent.putExtra("patient",patients.get(position))
                //intent.putExtra("position",position)
                //startActivityForResult(intent, PatientListActivity.REQUEST_EDITER)
                true
            }
            R.id.action_delete -> {
                // Handle the "Delete" option
                // Show the confirmation dialog when "Delete" is selected
                val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
                val position = info.position
                Toast.makeText(this,"User:"+position.toString(),Toast.LENGTH_LONG).show()
                showDeleteConfirmationDialog(position)
                true
            }

            R.id.action_new_product -> {
                // Handle the "new product" option
                val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
                val position = info.position
                Toast.makeText(this,"User:"+position.toString(),Toast.LENGTH_LONG).show()
                var listProduct : MutableList<Product>
                listProduct = mutableListOf(
                    Product(0,listUsers[position].uid,"Coca Cola","Bebestible","1686","27/11/2023")
                )
                UserWithProducts(
                    listUsers[position],
                    listProduct
                )
                db.productDao().insertAll(listProduct[0])
                refreshFromDatabase()
                true
            }
            // Add cases for other options as needed
            else -> {
                super.onContextItemSelected(item)
            }
        }
    }

    private fun showDeleteConfirmationDialog(itemPosition: Int) {
        val builder = AlertDialog.Builder(this)

        builder.setMessage(R.string.message_delete_patient)
        builder.setPositiveButton(R.string.btn_delete) { dialog, _ ->
            // Handle the delete action here
            deleteItem(itemPosition)
        }
        builder.setNegativeButton(R.string.btn_cancel) { dialog, _ ->
            dialog.dismiss()
        }
        builder.create().show()
    }
    private fun deleteItem(itemPosition: Int) {

        deleteFromDatabase(listUsers[itemPosition])
        refreshFromDatabase()
    }

    public fun saveUserToDatabase(user : User) {
        if (user != null){
            db.userDao().insertAll(user)
            refreshFromDatabase()
        }
    }

    public fun deleteFromDatabase(user : User) {
        db.userDao().delete(user)
    }

    public fun refreshFromDatabase(){
        val list : List<User> = db.userDao().getAll()
        val listUserWith : List<UserWithProducts> = db.userDao().getUsersWithPlaylists()
        listUserWith.forEach {
            Log.i("UserRoomExampleActivity","Data user:"+it.user.firstName)
            Log.i("UserRoomExampleActivity","Data products of user:"+it.products.toString())
        }
        Toast.makeText(this,"Hi: "+list.size.toString(),Toast.LENGTH_LONG).show()
        listUsers = list.toMutableList()
        Toast.makeText(this,"Hi: "+listUsers.size.toString(),Toast.LENGTH_LONG).show()
        listViewUsers.invalidate()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listUsers.map { it.firstName })
        listViewUsers.adapter = adapter
    }

}