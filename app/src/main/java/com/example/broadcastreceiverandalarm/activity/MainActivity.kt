package com.example.broadcastreceiverandalarm.activity

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.broadcastreceiverandalarm.R
import com.example.broadcastreceiverandalarm.receiver.MyBroadcastReceiver
import com.example.broadcastreceiverandalarm.receiver.SmsBroadcastReceiver
import com.google.android.gms.auth.api.phone.SmsRetriever

class MainActivity : AppCompatActivity() {
    private lateinit var myBroadcastReceiver: MyBroadcastReceiver
    private lateinit var smsBroadcastReceiver: SmsBroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startSmsUserConsent()
        registerSmsReceiver()

//        myBroadcastReceiver = MyBroadcastReceiver()
//        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
//        registerReceiver(myBroadcastReceiver, intentFilter)

    }

    private fun registerSmsReceiver(){
        smsBroadcastReceiver = SmsBroadcastReceiver()

        smsBroadcastReceiver.smsBroadcastReceiverListener = object : SmsBroadcastReceiver.SmsBroadcastReceiverListener {
            override fun onSuccess(intent: Intent) {
                val message = intent.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE)
                Toast.makeText(this@MainActivity, message,Toast.LENGTH_LONG).show()
            }

            override fun onFailure() {

            }
        }

        val intentFilter = IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION)
        registerReceiver(smsBroadcastReceiver, intentFilter)
    }

    private fun startSmsUserConsent(){
        val client = SmsRetriever.getClient(this)
        client.startSmsUserConsent(null)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(myBroadcastReceiver)
    }
}