package com.example.uielement2

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.view.get

class AlbumDetails : AppCompatActivity() {

    private  var arrayAdapter:ArrayAdapter<String> ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_details)

        var modalItems: Modal = intent.getSerializableExtra("data") as Modal

        val viewImage = findViewById<ImageView>(R.id.viewImage)
        val album_name = findViewById<TextView>(R.id.nameOfAlbum)
        val album_list = findViewById<ListView>(R.id.albumList)

        viewImage.setImageResource(modalItems.image!!)
        album_name.text = modalItems.name

        if(modalItems.name=="Touhou Project"){
            arrayAdapter = ArrayAdapter(applicationContext, R.layout.text_view, MainActivity.touhouProject)
            album_list?.adapter = arrayAdapter
            registerForContextMenu(album_list)
        }
        else if(modalItems.name=="Vocaloid"){
            arrayAdapter = ArrayAdapter(applicationContext, R.layout.text_view, MainActivity.vocaloid)
            album_list?.adapter = arrayAdapter
            registerForContextMenu(album_list)
        }
        else if(modalItems.name=="Honeyworks"){
            arrayAdapter = ArrayAdapter(applicationContext, R.layout.text_view, MainActivity.honeyworks)
            album_list?.adapter = arrayAdapter
            registerForContextMenu(album_list)
        }
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
        var modalItems: Modal = intent.getSerializableExtra("data") as Modal
        val album_list = findViewById<ListView>(R.id.albumList)
        return when (item.itemId) {
            R.id.delete -> {

                val dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setMessage("Are you sure you want to remove this song from the album ${modalItems.name}?")
                        .setCancelable(false)
                        .setPositiveButton("CONFIRM", DialogInterface.OnClickListener { _, _ ->

                            when (modalItems.name) {
                                "Touhou Project" -> {
                                    MainActivity.touhouProject = remove(MainActivity.touhouProject, info.position)
                                    arrayAdapter = ArrayAdapter(applicationContext, R.layout.text_view, MainActivity.touhouProject)
                                    album_list?.adapter = arrayAdapter
                                    Toast.makeText(applicationContext, "Song removed", Toast.LENGTH_SHORT).show()
                                }
                                "Vocaloid" -> {
                                    MainActivity.vocaloid = remove (MainActivity.vocaloid, info.position)
                                    arrayAdapter = ArrayAdapter(applicationContext, R.layout.text_view, MainActivity.vocaloid)
                                    album_list?.adapter = arrayAdapter
                                    Toast.makeText(applicationContext, "Song removed", Toast.LENGTH_SHORT).show()
                                }
                                "Honeyworks" -> {
                                    MainActivity.honeyworks = remove(MainActivity.honeyworks, info.position)
                                    arrayAdapter = ArrayAdapter(applicationContext, R.layout.text_view, MainActivity.honeyworks)
                                    album_list?.adapter = arrayAdapter
                                    Toast.makeText(applicationContext, "Song removed", Toast.LENGTH_SHORT).show()
                                }
                            }
                        })
                        .setNegativeButton("CANCEL", DialogInterface.OnClickListener { dialog, _ ->
                            dialog.cancel()
                        })
                val alert = dialogBuilder.create()
                alert.setTitle("Remove from albums")
                alert.show()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}