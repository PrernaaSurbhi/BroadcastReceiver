package com.example.broadcastrecv

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.battery_fragment_layout.*

class BatteryFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.battery_fragment_layout, container, false)
    }

    override fun onStart() {
        super.onStart()
        val intentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        activity?.registerReceiver(onBattery, intentFilter)
    }

    override fun onStop() {
        super.onStop()
        activity?.unregisterReceiver(onBattery)
    }

    var onBattery: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Toast.makeText(context, "batteryBroadcast", Toast.LENGTH_SHORT).show()

            val percentage = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL, 1)

            progress_bar.progress = percentage ?: 0

            level.text = percentage.toString()

            when (intent?.getIntExtra(BatteryManager.EXTRA_STATUS, -1)) {
                BatteryManager.BATTERY_STATUS_CHARGING -> {
                    status.setImageResource(R.drawable.charging)
                }

                BatteryManager.BATTERY_STATUS_FULL -> {
                    val plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1)

                    if (plugged == BatteryManager.BATTERY_PLUGGED_AC
                            || plugged == BatteryManager.BATTERY_PLUGGED_USB) {
                        status.setImageResource(R.drawable.full)
                    } else {
                        status.setImageResource(R.drawable.unplugged)
                    }
                }

                else ->{
                    status.setImageResource(R.drawable.unplugged)
                }

            }


        }

    }


}