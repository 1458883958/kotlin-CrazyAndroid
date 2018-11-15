package com.wdl.crazyandroiddemo.service

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.wdl.crazyandroiddemo.R
import com.wdl.crazyandroiddemo.ServiceActivity

class MyService : Service() {

    private val binder:Binder = MyBinder()
    private class MyBinder : Binder() {
         fun count() = 5
    }
    override fun onBind(intent: Intent): IBinder? {
        Log.e("wdl", "------onBind()-----")
        return binder
    }

    override fun onCreate() {
        super.onCreate()
        Log.e("wdl", "------onCreate()-----")
        val intent = Intent(this,ServiceActivity::class.java)
        val pi = PendingIntent.getActivity(this,0,intent,0)
        val notification = Notification(R.drawable.ic_launcher_background,"hello",System.currentTimeMillis())
        notification.contentIntent = pi
        startForeground(1,notification)

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("wdl", "------onStartCommand()-----")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.e("wdl", "------onUnbind()-----")
        return true
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
        Log.e("wdl", "------onRebind()-----")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("wdl", "------onDestroy()-----")
    }


}
