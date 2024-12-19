package com.example.listaLudzi

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listaLudzi.R
import org.json.JSONArray
import org.json.JSONObject

class OsobyAdapter(
    private val osoby: MutableList<String>,
    private val sharedPreferences: SharedPreferences
) : RecyclerView.Adapter<OsobyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OsobyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_osoba, parent, false)
        return OsobyViewHolder(view)
    }

    override fun onBindViewHolder(holder: OsobyViewHolder, position: Int) {
        val osoba = osoby[position]
        holder.bind(osoba)
        holder.btnUsun.setOnClickListener {
            osoby.removeAt(position)
            notifyItemRemoved(position)

            val updatedList = JSONArray()
            for (osoba in osoby) {
                updatedList.put(osoba)
            }
            sharedPreferences.edit().putString("osoby", updatedList.toString()).apply()
        }
    }

    override fun getItemCount(): Int = osoby.size
}
