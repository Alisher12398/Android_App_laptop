package com.android.android_app.Activity

import android.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.android.android_app.R
import android.view.View


var fTrans: FragmentTransaction? = null

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val adapter = PageAdapter(getSupportFragmentManager())
        pager.adapter = adapter*/

    }

   /* fun onClick(v: View) {
        fTrans = fragmentManager.beginTransaction()
        when (v.getId()) {
            R.id.btnAdd -> fTrans.add(R.id.frgmCont, frag1)
            R.id.btnRemove -> fTrans.remove(frag1)
            R.id.btnReplace -> fTrans.replace(R.id.frgmCont, frag2)
            else -> {
            }
        }
        if (chbStack.isChecked()) fTrans.addToBackStack(null)
        fTrans.commit()
    }*/
}
