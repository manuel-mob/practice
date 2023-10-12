package cl.mmoscoso.practice.entity

import android.os.Parcel
import android.os.Parcelable

data class Patient(
    val name: String?,
    val email: String?,
    val room: String?,
    val age: Int,
    val symptoms: List<String>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.createStringArrayList() ?: emptyList()

    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(email)
        parcel.writeString(room)
        parcel.writeInt(age)
        parcel.writeStringList(symptoms)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Patient> {
        override fun createFromParcel(parcel: Parcel): Patient {
            return Patient(parcel)
        }

        override fun newArray(size: Int): Array<Patient?> {
            return arrayOfNulls(size)
        }
    }
}
