package com.sanjeet.uploadexcelsheet.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class, Student::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun studentDao(): StudentDao

    companion object {

        @Volatile
        private var INSTANCE: UserDatabase? = null

        public fun getDatabase(context: Context): UserDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext, UserDatabase::class.java,
                        "userDB"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }

}