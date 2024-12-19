package com.example.listaLudzi

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.listaLudzi.R
import com.google.android.material.textfield.TextInputEditText
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("listaLudzi", MODE_PRIVATE)

        val etImie = findViewById<TextInputEditText>(R.id.etImie)
        val etNazwisko = findViewById<TextInputEditText>(R.id.etNazwisko)
        val etWiek = findViewById<TextInputEditText>(R.id.etWiek)
        val etWzrost = findViewById<TextInputEditText>(R.id.etWzrost)
        val etWaga = findViewById<TextInputEditText>(R.id.etWaga)
        val btnZapisz = findViewById<Button>(R.id.btnZapisz)
        val btnEkran2 = findViewById<Button>(R.id.btnEkran2)

        btnZapisz.setOnClickListener {
            val imie = etImie.text.toString()
            val nazwisko = etNazwisko.text.toString()
            val wiek = etWiek.text.toString().toIntOrNull()
            val wzrost = etWzrost.text.toString().toDoubleOrNull()
            val waga = etWaga.text.toString().toDoubleOrNull()

            if (imie.isBlank() || nazwisko.isBlank() || wiek == null || wzrost == null || waga == null) {
                Toast.makeText(this, "Wszystkie pola muszą być wypełnione poprawnie", Toast.LENGTH_SHORT).show()
            } else if (wiek <= 0 || wzrost !in 50.0..250.0 || waga !in 3.0..200.0) {
                Toast.makeText(this, "Podano nieprawidłowe wartości", Toast.LENGTH_SHORT).show()
            } else {
                val osoba = """{"imie":"$imie","nazwisko":"$nazwisko","wiek":$wiek,"wzrost":$wzrost,"waga":$waga}"""
                val listaOsob = JSONArray(sharedPreferences.getString("osoby", "[]"))
                listaOsob.put(osoba)

                sharedPreferences.edit().putString("osoby", listaOsob.toString()).apply()
                Toast.makeText(this, "Dane zapisane", Toast.LENGTH_SHORT).show()

                etImie.text?.clear()
                etNazwisko.text?.clear()
                etWiek.text?.clear()
                etWzrost.text?.clear()
                etWaga.text?.clear()
            }
        }

        btnEkran2.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }
}
