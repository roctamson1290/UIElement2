package com.example.uielement2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView

class add2queue : AppCompatActivity() {

    private var listView: ListView? = null
    private  var arrayAdapter: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add2queue)


        listView = findViewById(R.id.queueList)
        arrayAdapter = ArrayAdapter(applicationContext, R.layout.text_view, MainActivity.songQueue)
        listView?.adapter = arrayAdapter

    }
}