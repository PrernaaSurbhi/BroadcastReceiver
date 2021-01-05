package com.example.broadcastrecv

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class OnBootReceiver:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("12344","onBootBroadcastReceive")
        Toast.makeText(context, "OnBootreceive", Toast.LENGTH_LONG).show()
    }
}