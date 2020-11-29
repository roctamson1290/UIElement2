package com.example.uielement2

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class add2queue : AppCompatActivity() {

    private var listView: ListView? = null
    private  var arrayAdapter: ArrayAdapter<String>? = null
    private val channel_id = "channel_id"
    private val notification_id = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add2queue)


        listView = findViewById(R.id.queueList)
        arrayAdapter = ArrayAdapter(applicationContext, R.layout.text_view, MainActivity.songQueue)
        listView?.adapter = arrayAdapter
        registerForContextMenu(listView)
    }

    private fun remove(arr: Array<String>, element: Int): Array<String> {
        if (element < 0 || element >= arr.size) {
            return arr
        }
        val result = arr.toMutableList()
        result.removeAt(element)
        return result.toTypedArray()
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        menuInflater.inflate(R.menu.delete_menu, menu)
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        return when(item.itemId){
            R.id.delete->{
                MainActivity.songQueue = remove(MainActivity.songQueue, info.position)
                arrayAdapter = ArrayAdapter(applicationContext, R.layout.text_view, MainActivity.songQueue)
                listView?.adapter = arrayAdapter
                Toast.makeText(applicationContext,"Song removed", Toast.LENGTH_SHORT).show()
                if(MainActivity.songQueue.isEmpty()){
                    createNotificationChannel()
                    sendNotification()
                }
                return true
            }
            else->super.onContextItemSelected(item)
        }
    }

    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "UI Elements 2"
            val descriptionText = "Your songs queue is empty"
            val importance:Int = NotificationManager.IMPORTANCE_DEFAULT
            val channel:NotificationChannel = NotificationChannel(channel_id,name,importance).apply {
                description = descriptionText
            }
            val notificationManager:NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification(){
        val builder = NotificationCompat.Builder(this, channel_id)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("UI Elements 2")
                .setContentText("Your songs queue is empty")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        with(NotificationManagerCompat.from(this)){
            notify(notification_id, builder.build())
        }
    }
}