package com.wdl.crazyandroiddemo.service

import android.app.IntentService
import android.content.Intent
import android.util.Log


/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * TODO: Customize class - update intent actions and extra parameters.
 */
class MyIntentService : IntentService("MyIntentService") {

    override fun onHandleIntent(intent: Intent?) {
        //直接执行耗时操作
        val endTime = System.currentTimeMillis()+20*1000
        Log.e("wdl","onHandleIntent")
        while (System.currentTimeMillis()<endTime){
            synchronized(this){
                Thread.sleep(endTime-System.currentTimeMillis())
                Log.e("wdl","synchronized")
            }
        }
    }


}
