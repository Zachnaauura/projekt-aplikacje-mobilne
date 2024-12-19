package com.example.listaLudzi

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listaLudzi.R
import org.json.JSONArray

class SecondActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        sharedPreferences = getSharedPreferences("listaLudzi", MODE_PRIVATE)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val listaOsob = JSONArray(sharedPreferences.getString("osoby", "[]"))
        val osoby = mutableListOf<String>()

        for (i in 0 until listaOsob.length()) {
            osoby.add(listaOsob.getString(i))
        }

        recyclerView.adapter = OsobyAdapter(osoby, sharedPreferences)
    }
}
