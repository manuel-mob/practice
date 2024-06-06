package cl.mmoscoso.practice.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import cl.mmoscoso.practice.R
import cl.mmoscoso.practice.entity.Patient

class PatientListAdapterCustomButtonItem(
    context: Context,
    resource: Int,
    patients: List<Patient>
) : ArrayAdapter<Patient>(context, resource, patients) {

    var onEditClickListener: ((Patient,Int) -> Unit)? = null

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val listItemView = convertView ?: inflater.inflate(R.layout.list_item_custom_patient, null)

        // Get the patient data at the current position
        val patient = getItem(position)

        // Bind patient data to TextViews in the custom layout
        val nameTextView = listItemView.findViewById<TextView>(R.id.textViewName)
        val emailTextView = listItemView.findViewById<TextView>(R.id.textViewEmail)
        val roomTextView = listItemView.findViewById<TextView>(R.id.textViewRoom)
        val ageTextView = listItemView.findViewById<TextView>(R.id.textViewAge)
        val buttonEdit = listItemView.findViewById<TextView>(R.id.buttonEditItemPatient)

        buttonEdit.setOnClickListener{
            if (patient != null) {
                onEditClickListener?.invoke(patient,position)
            }
        }


        // Set the patient data in the TextViews
        nameTextView.text = patient?.name
        emailTextView.text = patient?.email
        roomTextView.text = patient?.room
        ageTextView.text = patient?.age.toString()

        return listItemView
    }


}
