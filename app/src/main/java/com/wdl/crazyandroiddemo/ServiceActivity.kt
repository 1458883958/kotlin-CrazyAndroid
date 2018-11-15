package com.wdl.crazyandroiddemo

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import com.wdl.crazyandroiddemo.service.MyIntentService
import com.wdl.crazyandroiddemo.service.MyService
import kotlinx.android.synthetic.main.activity_service.*

class ServiceActivity : AppCompatActivity() {

    private val conn = object :ServiceConnection{
        override fun onServiceDisconnected(name: ComponentName?) {
            Log.e("wdl","异常断开连接-------")
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.e("wdl","连接-------")
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)
        mStart.setOnClickListener {
            val intent = Intent(this,MyService::class.java)
            startService(intent)
        }
        mStop.setOnClickListener {
            val intent = Intent(this,MyService::class.java)
            stopService(intent)
        }
        mBindService.setOnClickListener {
            val intent = Intent(this,MyService::class.java)
            bindService(intent,conn,Service.BIND_AUTO_CREATE)
        }
        mUnBindService.setOnClickListener {
            unbindService(conn)
        }
    }
}
