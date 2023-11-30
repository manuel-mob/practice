package cl.mmoscoso.practice.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "user_id") val userid : Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "category_product") val categoryProduct: String?,
    @ColumnInfo(name = "amount") var amount: String?,
    @ColumnInfo(name = "date") val date: String?
)