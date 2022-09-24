package com.example.broadcastreceiverandalarm.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast

class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        if(isConnectedToInternet(p0!!)){
            Toast.makeText(p0, "Interned Connected", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(p0, "Interned Disconnected", Toast.LENGTH_LONG).show()
        }
    }


    private fun isConnectedToInternet(context: Context) : Boolean{
        val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activityNetwork = cm.activeNetworkInfo
        return activityNetwork != null && activityNetwork.isConnectedOrConnecting
    }
}