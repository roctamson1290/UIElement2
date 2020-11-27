package com.example.uielement2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var listView:ListView ? = null
    private  var arrayAdapter:ArrayAdapter<String> ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.context_list_view)
        arrayAdapter = ArrayAdapter(applicationContext, R.layout.text_view, itemList)
        listView?.adapter = arrayAdapter
        registerForContextMenu(listView)
    }

    private fun append(arr: Array<String>, element: String): Array<String>{
        val list: MutableList<String> = arr.toMutableList()
        list.add(element)
        return list.toTypedArray()
    }

    companion object {
        var songQueue: Array<String> = emptyArray()

        private var itemList = arrayOf(
                "Bad Apple",
                "No Life Queen",
                "Moon Song",
                "Collector's High",
                "Mischievous Sensation",
                "Deep Sea Girl",
                "Assassin Princess",
                "Echo",
                "Fall Asleep",
                "Love Trial",
                "Mona",
                "Monday's Melancholy",
                "Watashi no tenshi ",
                "No.1／mona",
                "Shisulove")

        var albumImage = intArrayOf(
                R.drawable.touhou_project,
                R.drawable.vocaloid,
                R.drawable.honeyworks
        )

        val album_names = arrayOf(
                "Touhou Project",
                "Vocaloid",
                "Honeyworks"
        )

        val touhouProject = arrayOf(
                "Bad Apple",
                "No Life Queen",
                "Moon Song",
                "Collector's High",
                "Mischievous Sensation"
        )

        val vocaloid = arrayOf(
                "Deep Sea Girl",
                "Assassin Princess",
                "Echo",
                "Fall Asleep",
                "Love Trial"
        )

        val honeyworks = arrayOf(
                "Mona",
                "Monday's Melancholy",
                "Watashi no tenshi ",
                "No.1／mona",
                "Shisulove"
        )
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menuInflater.inflate(R.menu.queue_menu, menu)
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        return when(item.itemId){
            R.id.add2queue->{
                songQueue = append(songQueue, itemList[info.position])
                Toast.makeText(applicationContext, "Added to queue", Toast.LENGTH_SHORT).show()
                return true
            }
            else->return super.onContextItemSelected(item)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater: MenuInflater = menuInflater
        menuInflater.inflate(R.menu.options, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.songs ->{
                Toast.makeText(applicationContext, "Song", Toast.LENGTH_SHORT).show()

            }
            R.id.albums ->{
                val albumViewList = Intent(this, album::class.java)
                startActivity(albumViewList)
            }
            R.id.queue ->{
                val added2queue = Intent(this, add2queue::class.java)
                startActivity(added2queue)
            }
        }
        return true
    }
}