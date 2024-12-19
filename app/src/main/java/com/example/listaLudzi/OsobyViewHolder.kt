package com.example.listaLudzi

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.listaLudzi.R
import org.json.JSONObject

class OsobyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textImieNazwisko: TextView = itemView.findViewById(R.id.textImieNazwisko)
    val textWiek: TextView = itemView.findViewById(R.id.textWiek)
    val textWzrost: TextView = itemView.findViewById(R.id.textWzrost)
    val textWaga: TextView = itemView.findViewById(R.id.textWaga)
    val btnUsun: Button = itemView.findViewById(R.id.btnUsun)

    fun bind(osoba: String) {
        val json = JSONObject(osoba)
        textImieNazwisko.text = "${json.getString("imie")} ${json.getString("nazwisko")}"
        textWiek.text = "Wiek: ${json.getInt("wiek")}"
        textWzrost.text = "Wzrost: ${json.getDouble("wzrost")}"
        textWaga.text = "Waga: ${json.getDouble("waga")}"
    }
}
