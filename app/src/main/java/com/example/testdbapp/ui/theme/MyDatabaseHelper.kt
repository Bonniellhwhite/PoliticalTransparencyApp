package com.example.testdbapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.util.Log

class MyDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "my_database.db"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Create your database tables here
        db.execSQL("CREATE TABLE IF NOT EXISTS my_table (id INTEGER PRIMARY KEY, name TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Handle database schema upgrades here
        if (oldVersion < 2) {
            Log.d("MyApp", "Version2")
        }
        // Add more upgrade logic for other versions as needed
    }

    fun insertData(name: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("name", name)
        }
        val newRowId = db.insert("my_table", null, values)
        //db.close()
        return newRowId
    }


}
