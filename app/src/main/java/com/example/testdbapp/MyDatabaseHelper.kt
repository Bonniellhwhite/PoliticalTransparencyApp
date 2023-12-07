package com.example.testdbapp

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.util.Log
import android.database.Cursor

class MyDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "my_database.db"
        private const val DATABASE_VERSION = 1
    }
    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL("CREATE TABLE IF NOT EXISTS my_table (id INTEGER PRIMARY KEY, name TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 2) {
            Log.d("MyApp", "Database upgraded to version 2")
        }
    }

    fun insertData(name: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("name", name)
        }
        return db.insert("my_table", null, values)
    }

    @SuppressLint("Range")
    fun readAllData(): String {
        val db = readableDatabase
        var dataString = ""

        val cursor: Cursor = db.rawQuery("SELECT * FROM my_table", null)
        if (cursor.moveToFirst()) {
            do {
                val name = cursor.getString(cursor.getColumnIndex("name"))
                dataString += "$name\n"  // Append each name to the string, followed by a newline
            } while (cursor.moveToNext())
        }
        cursor.close()

        return dataString
    }
    fun clearDatabase() {
        val db = writableDatabase
        db.execSQL("DELETE FROM my_table") // Replace 'my_table' with your table name
        // Repeat for other tables if necessary
    }

}
