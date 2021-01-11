package com.example.broadcastrecv


import android.os.Bundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getSupportFragmentManager().beginTransaction()
            .replace(R.id.battery_fragment, BatteryFragment() ).commit()

        button.setOnClickListener {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.note_container, NoteFragment() ).commit()
        }
    }
}