package com.lermadev.tictactoe

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    lateinit var player1: EditText
    lateinit var player2: EditText
    lateinit var btnJugar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        player1 = findViewById(R.id.txt_player_1)
        player2 = findViewById(R.id.txt_player_2)
        btnJugar = findViewById(R.id.btn_jugar)

        player1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrEmpty()) {
                    player1.setBackgroundResource(R.drawable.border)
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })
        player2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrEmpty()) {
                    player2.setBackgroundResource(R.drawable.border)
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        btnJugar.setOnClickListener {
            val nombre1 = player1.text.toString().trim()
            val nombre2 = player2.text.toString().trim()

            // Validar si alguno de los campos está vacío
            if (nombre1.isEmpty()) {
                player1.error = "Ingresa el nombre del Jugador 1"
                player1.setBackgroundResource(R.drawable.border_error)
                player1.requestFocus()
                return@setOnClickListener
            }

            if (nombre2.isEmpty()) {
                player2.error = "Ingresa el nombre del Jugador 2"
                player2.setBackgroundResource(R.drawable.border_error)
                player2.requestFocus()
                return@setOnClickListener
            }

            // Si ambos tienen texto, procedemos a abrir MainActivity
            val intent = Intent(applicationContext, GameActivity::class.java).apply {
                putExtra("player1", nombre1)
                putExtra("player2", nombre2)
            }
            startActivity(intent)
            finish()
        }
    }
}