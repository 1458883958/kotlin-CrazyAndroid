package com.wdl.crazyandroiddemo

import android.annotation.SuppressLint
import android.content.ContentValues
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sqlite.*

class SQLiteActivity : AppCompatActivity() {

    @SuppressLint("Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite)
        val helper = MySQLiteHelper(this, "demo.db", null, 3)
        val db = helper.writableDatabase
        mSave.setOnClickListener {
            val name = mName.text.toString()
            val age = mAge.text.toString().toInt()
            val contentValue = ContentValues()
            contentValue.put("name", name)
            contentValue.put("age", age)
            //插入
            val index = db.insert("student", null, contentValue).toInt()
            if (index == -1) Toast.makeText(this, "插入失败", Toast.LENGTH_SHORT).show()
        }
        mQuery.setOnClickListener {
            //查询
            val cursor = db.query("student", null, "name like ?", arrayOf("lijie"), null, null, null)
            while (cursor.moveToNext()) {
                val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                val age = cursor.getInt(cursor.getColumnIndexOrThrow("age"))
                Log.e("wdl", "name = $name,age = $age")
            }
            cursor.close()
        }
        mUpdate.setOnClickListener {
            val contentValue = ContentValues()
            val age = mAge1.text.toString().toInt()
            contentValue.put("age", age)
            //更新
            val index = db.update("student", contentValue, "name = ?", arrayOf("lijie"))
            if (index != -1) Toast.makeText(this, "更新成功", Toast.LENGTH_SHORT).show()
        }

        mDel.setOnClickListener {
            val index = db.delete("student","name = ?", arrayOf("wudelin"))
            if (index != -1) Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show()
        }
    }
}
