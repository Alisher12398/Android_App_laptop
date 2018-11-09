package com.android.android_app.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.android.android_app.DBHelper
import com.android.android_app.R

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val myDatabase = DBHelper(this).readableDatabase

        //myDatabase.rawQuery("SELECT * FROM my_awesome_table")
    }
}
