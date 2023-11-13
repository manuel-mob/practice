package cl.mmoscoso.practice.database

import androidx.room.Database
import androidx.room.RoomDatabase
import cl.mmoscoso.practice.dao.UserDao
import cl.mmoscoso.practice.entity.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}