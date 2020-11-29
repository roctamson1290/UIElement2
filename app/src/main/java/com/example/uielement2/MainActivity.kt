package com.example.uielement2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

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

        var itemList = arrayOf(
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

        var album_names = arrayOf(
                "Touhou Project",
                "Vocaloid",
                "Honeyworks"
        )

        var touhouProject = arrayOf(
                "Bad Apple",
                "No Life Queen",
                "Moon Song",
                "Collector's High",
                "Mischievous Sensation"
        )

        var vocaloid = arrayOf(
                "Deep Sea Girl",
                "Assassin Princess",
                "Echo",
                "Fall Asleep",
                "Love Trial"
        )

        var honeyworks = arrayOf(
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
                val snackBar = Snackbar.make(listView!!.findViewById(R.id.context_list_view), "Song added to queue", Snackbar.LENGTH_LONG)
                snackBar.setAction("See Queue"){
                    val added2queue = Intent(this, add2queue::class.java)
                    startActivity(added2queue)
                }
                snackBar.show()
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
                Toast.makeText(applicationContext, "Already in song list", Toast.LENGTH_SHORT).show()
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