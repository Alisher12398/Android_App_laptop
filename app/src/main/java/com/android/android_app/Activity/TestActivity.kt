package com.android.android_app.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.android.android_app.DBHelper
import com.android.android_app.Models.FoodsModel
import com.android.android_app.R

lateinit var foods : List<FoodsModel>

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val test_textview_1 = findViewById<TextView>(R.id.test_textview_1)
        val test_textview_2 = findViewById<TextView>(R.id.test_textview_2)
        val test_textview_3 = findViewById<TextView>(R.id.test_textview_3)
        val test_textview_4 = findViewById<TextView>(R.id.test_textview_4)



        val db = DBHelper(this)

        db.readableDatabase

        //val myDatabase = DBHelper(this).readableDatabase

        /*val selectQuery = "SELECT  * FROM food"
        val cursor = db.rawQuery(selectQuery, null)*/

        //val foods = db.parceDBtoList()

        test_textview_1.text = "test"

        foods = db.parceDBtoList()
        test_textview_1.text = "id_food: " + foods[0].id_foods.toString()
        test_textview_2.text = "\nid_product: " +foods[0].id_category.toString()
        test_textview_3.text = "\nfood_name: " +foods[0].name
        test_textview_4.text = "\nfood_desc: " +foods[0].description



        //myDatabase.rawQuery("SELECT * FROM my_awesome_table")
    }
}
