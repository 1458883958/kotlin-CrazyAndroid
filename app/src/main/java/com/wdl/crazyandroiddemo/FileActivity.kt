package com.wdl.crazyandroiddemo

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_file.*
import java.io.BufferedReader
import java.io.InputStreamReader

class FileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file)
        //this.getPreferences(Context.MODE_PRIVATE)
        //PreferenceManager.getDefaultSharedPreferences(this)
        AppSharedUtil.put(this,"name","wdl")
        AppSharedUtil.put(this,"age",23)
        AppSharedUtil.put(this,"man",true)
        AppSharedUtil.put(this,"price",23L)
        mFileSave.setOnClickListener {
            val content = mContent.text.toString()
            Log.e("wdl", content)
            saveFile(content)
            mContent.setText("")
        }
        mFileReader.setOnClickListener {
            readFile()
        }
    }


    private fun readFile() {
//        val fileInputStream = openFileInput("data")
//        val reader = BufferedReader(InputStreamReader(fileInputStream))
//        val content = StringBuffer()
//        content.append(reader.readLine())
        val content = FileWRTool.readFile(this,"data")
        Log.e("wdl", content)
    }

    private fun saveFile(content: String) {
//        val fileOutputStream = this.openFileOutput("data",Context.MODE_PRIVATE)
//        val writer = BufferedWriter(OutputStreamWriter(fileOutputStream))
//        writer.write(content)
        FileWRTool.writeFile(this, "data", content)

    }
}
