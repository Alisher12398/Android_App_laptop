package com.android.android_app

import android.content.Context
import android.content.SharedPreferences
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.android.android_app.Models.FoodsModel
import java.io.File
import java.io.FileOutputStream

class DBHelper(val context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    private val preferences: SharedPreferences = context.getSharedPreferences(
            "${context.packageName}.database_versions",
            Context.MODE_PRIVATE
    )

    private fun installedDatabaseIsOutdated(): Boolean {
        return preferences.getInt(DATABASE_NAME, 0) < DATABASE_VERSION
    }

    private fun writeDatabaseVersionInPreferences() {
        preferences.edit().apply {
            putInt(DATABASE_NAME, DATABASE_VERSION)
            apply()
        }
    }

    private fun installDatabaseFromAssets() {
       // context.deleteDatabase(DATABASE_NAME)
        val toast = Toast.makeText(context, "Deleted prev db", Toast.LENGTH_SHORT).show()
        val inputStream = context.assets.open("$ASSETS_PATH/$DATABASE_NAME.db")


        try {
            val outputFile = File(context.getDatabasePath(DATABASE_NAME).path)
            val outputStream = FileOutputStream(outputFile)

            inputStream.copyTo(outputStream)
            inputStream.close()

            outputStream.flush()
            outputStream.close()
        } catch (exception: Throwable) {
            throw RuntimeException("The $DATABASE_NAME database couldn't be installed.", exception)
        }
    }

    @Synchronized
    private fun installOrUpdateIfNecessary() {
        if (installedDatabaseIsOutdated()) {
            context.deleteDatabase(DATABASE_NAME)
            installDatabaseFromAssets()
            writeDatabaseVersionInPreferences()
        }
    }

    /*override fun getWritableDatabase(): SQLiteDatabase {
        throw RuntimeException("The $DATABASE_NAME database is not writable.")
    }*/

    override fun getReadableDatabase(): SQLiteDatabase {
        installDatabaseFromAssets()
       // installOrUpdateIfNecessary()
        return super.getReadableDatabase()
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // Nothing to do
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Nothing to do
    }

    fun parceDBtoList(): List<FoodsModel> {
        val newsList = ArrayList<FoodsModel>()
        val db = writableDatabase
        val selectQuery = "SELECT  * FROM food"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val food = FoodsModel()
                    food.id_foods = cursor.getInt(cursor.getColumnIndex("_ID"))
                    food.id_category = cursor.getInt(cursor.getColumnIndex("id_category"))
                    food.name = cursor.getString(cursor.getColumnIndex("name"))
                    food.description = cursor.getString(cursor.getColumnIndex("description"))
                    newsList.add(food)
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        return newsList
    }

    companion object {
        const val ASSETS_PATH = "databases"
        const val DATABASE_NAME = "android"
        const val DATABASE_VERSION = 1
    }



}