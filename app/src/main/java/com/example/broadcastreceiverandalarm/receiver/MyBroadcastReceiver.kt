package com.example.broadcastreceiverandalarm.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.content.Intent
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.ConnectivityManager
import android.widget.Toast

class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, p1: Intent?) {
        if(isConnectedToInternet(context!!)){
            Toast.makeText(context, "Interned Connected", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(context, "Interned Disconnected", Toast.LENGTH_LONG).show()
        }

        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
        val mediaPlayer = MediaPlayer.create(context, alarmSound)
        mediaPlayer.start()
        Toast.makeText(context, "Alarm manager is working", Toast.LENGTH_LONG).show()
    }


    private fun isConnectedToInternet(context: Context) : Boolean{
        val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activityNetwork = cm.activeNetworkInfo
        return activityNetwork != null && activityNetwork.isConnectedOrConnecting
    }
}