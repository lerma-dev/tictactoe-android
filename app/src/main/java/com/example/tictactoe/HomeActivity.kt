package com.example.tictactoe

import android.content.Intent
import android.os.Bundle
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

        btnJugar.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.putExtra("player1", player1.text.toString())
            intent.putExtra("player2", player2.text.toString())
            startActivity(intent)
        }
    }
}