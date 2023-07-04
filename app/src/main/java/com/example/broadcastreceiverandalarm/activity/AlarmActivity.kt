package com.example.broadcastreceiverandalarm.activity

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import com.example.broadcastreceiverandalarm.R
import com.example.broadcastreceiverandalarm.receiver.MyBroadcastReceiver
import kotlin.properties.Delegates

class AlarmActivity : AppCompatActivity() {

    private var currentTime by Delegates.notNull<Long>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)

        currentTime = SystemClock.elapsedRealtime()
        setAlarm()
    }

    private fun setAlarm() {
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        // MyBroadcastReceiver ishga tushiriladi
        val intent = Intent(this, MyBroadcastReceiver::class.java)
        val broadcast = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_IMMUTABLE)
        alarmManager.setExact(AlarmManager.ELAPSED_REALTIME, currentTime + 5000, broadcast)
    }
}