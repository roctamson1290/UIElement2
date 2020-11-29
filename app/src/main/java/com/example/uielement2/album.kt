package com.example.uielement2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import java.lang.reflect.Array

class album : AppCompatActivity() {

    var modalList = ArrayList<Modal>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)

        val names = MainActivity.album_names
        val images = MainActivity.albumImage

        for (i in names.indices){
            modalList.add(Modal(names[i], images[i]))
        }

        var customAdapter = CustomAdapter(modalList, this)
        val gridView = findViewById<GridView>(R.id.gridList)
        gridView.adapter = customAdapter

        gridView.setOnItemClickListener { adapterView, view, i, l ->
            var intent = Intent(this, AlbumDetails::class.java)
            intent.putExtra("data", modalList[i])
            startActivity(intent)
        }
    }

    class CustomAdapter(
            var itemModel: ArrayList<Modal>,
            context: Context
    ) : BaseAdapter(){

        var layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
            var view = view
            if(view == null){
                view = layoutInflater.inflate(R.layout.row_items, viewGroup, false)
            }

            var tvImageName = view?.findViewById<TextView>(R.id.albumName)
            var imageView = view?.findViewById<ImageView>(R.id.albumImage)

            tvImageName?.text = itemModel[position].name
            imageView?.setImageResource(itemModel[position].image!!)

            return view!!
        }

        override fun getItem(p0: Int): Any {
            return itemModel[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return itemModel.size
        }

    }
}