package com.ozge.myassigmentfirst

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, KmToMilesFragment())
                .commit()
        }

        findViewById<Button>(R.id.btnKmToMiles).setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, KmToMilesFragment())
                .commit()
        }

        findViewById<Button>(R.id.btnFeetToInches).setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, FeetToInchesFragment())
                .commit()
        }

        findViewById<Button>(R.id.btnLbsToKgs).setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, LbsToKgsFragment())
                .commit()
        }
    }
}
