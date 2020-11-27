package com.example.uielement2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

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
}