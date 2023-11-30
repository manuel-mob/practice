package cl.mmoscoso.practice.database

import androidx.room.Database
import androidx.room.RoomDatabase
import cl.mmoscoso.practice.dao.ProductDao
import cl.mmoscoso.practice.dao.UserDao
import cl.mmoscoso.practice.entity.Product
import cl.mmoscoso.practice.entity.User

@Database(entities = [User::class, Product::class], version = 4, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun productDao(): ProductDao
}