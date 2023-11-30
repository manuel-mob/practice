package cl.mmoscoso.practice.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import java.util.List;
import cl.mmoscoso.practice.entity.Product
import cl.mmoscoso.practice.entity.User

@Dao
interface ProductDao {
    @Query("SELECT * FROM product")
    fun getAll(): List<Product>

    @Query("SELECT * FROM product WHERE user_id = :userid ")
    fun findByUser(userid: Int): List<Product>

    @Insert
    fun insertAll(vararg products: Product)
}
