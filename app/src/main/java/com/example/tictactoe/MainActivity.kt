package com.example.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var img1:ImageView
    lateinit var img2:ImageView
    lateinit var img3:ImageView
    lateinit var img4:ImageView
    lateinit var img5:ImageView
    lateinit var img6:ImageView
    lateinit var img7:ImageView
    lateinit var img8:ImageView
    lateinit var img9:ImageView
    lateinit var imgX:ImageView
    lateinit var imgO:ImageView
    lateinit var btnResetGame:Button
    lateinit var btnResetScores:Button
    lateinit var lblVersus:TextView
    lateinit var lblRecordX:TextView
    lateinit var lblRecordO:TextView

    var isNext = "x"
    val jugadasGanadoras = arrayOf(
        // Verticales
        intArrayOf(1, 2, 3),
        intArrayOf(4, 5, 6),
        intArrayOf(7, 8, 9),
        // Horizontales
        intArrayOf(1, 4, 7),
        intArrayOf(2, 5, 8),
        intArrayOf(3, 6, 9),
        // Diagonales
        intArrayOf(1, 5, 9),
        intArrayOf(3, 5, 7)
    )

    var posX = IntArray(5)
    var posO = IntArray(5)
    var countX = 0
    var countO = 0
    var recordX = 0
    var recordO = 0
    var winner = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        img1 = findViewById(R.id.image_1)
        img2 = findViewById(R.id.image_2)
        img3 = findViewById(R.id.image_3)
        img4 = findViewById(R.id.image_4)
        img5 = findViewById(R.id.image_5)
        img6 = findViewById(R.id.image_6)
        img7 = findViewById(R.id.image_7)
        img8 = findViewById(R.id.image_8)
        img9 = findViewById(R.id.image_9)
        imgX = findViewById(R.id.image_x)
        imgO = findViewById(R.id.image_o)
        btnResetGame = findViewById(R.id.reset_game)
        btnResetScores = findViewById(R.id.reset_scores)
        lblVersus = findViewById(R.id.versus)
        lblRecordX = findViewById(R.id.record_x)
        lblRecordO = findViewById(R.id.record_o)

        btnResetGame.setOnClickListener {
            resetGame()
        }

        btnResetScores.setOnClickListener {
            recordX = 0
            recordO = 0
            lblRecordX.text = resources.getString(R.string.record_x)
            lblRecordO.text = resources.getString(R.string.record_o)
        }
    }

    fun clickImagen(view: View){
        val rutaNombre = resources.getResourceName(view.id)
        val clickNombre = rutaNombre.substring(rutaNombre.lastIndexOf("/") + 1)

        if(isNext == "x"){
            view.setBackgroundResource(R.drawable.gato_x)
            imgO.setBackgroundResource(R.drawable.player)
            imgX.setBackgroundResource(0)
        }
        else{
            view.setBackgroundResource(R.drawable.gato_o)
            imgX.setBackgroundResource(R.drawable.player)
            imgO.setBackgroundResource(0)
        }
        view.isEnabled=false

        for(i in 1..9){
            if(clickNombre == "image_$i") {
                if(isNext == "x"){
                    posX[countX]=i
                    val winnerX = checkWinner(posX)
                    if(winnerX != null){
                        winner = resources.getString(R.string.win_x)
                        for(pos in winnerX){
                            val imgsWinners = obtenerImagen(pos)
                            imgsWinners.setBackgroundResource(R.drawable.winner)
                        }
                        recordX += 1
                        lblRecordX.text = recordX.toString()
                        lockImage()
                    }
                    countX++
                } else {
                    posO[countO]=i
                    val winnerO = checkWinner(posO)
                    if(winnerO != null){
                        winner = resources.getString(R.string.win_o)
                        for(pos in winnerO){
                            val imgsWinners = obtenerImagen(pos)
                            imgsWinners.setBackgroundResource(R.drawable.winner)
                        }
                        recordO += 1
                        lblRecordO.text = recordO.toString()
                        lockImage()
                    }
                    countO++
                }
                break
            }
        }

        isNext = if(isNext == "x") "o" else "x"

        if (winner.isNotEmpty()) {
            lblVersus.text = winner
        } else if (countX + countO == 9) {
            lblVersus.text = resources.getString(R.string.draw)
        }
    }

    fun checkWinner(positions: IntArray): IntArray? {
        for(combinacion in jugadasGanadoras ){
            if( positions.contains(combinacion[0]) &&
                positions.contains(combinacion[1]) &&
                positions.contains(combinacion[2]) ) {
                return combinacion
            }
        }
        return null
    }

    fun obtenerImagen(numero: Int): ImageView {
        return when(numero) {
            1 -> img1
            2 -> img2
            3 -> img3
            4 -> img4
            5 -> img5
            6 -> img6
            7 -> img7
            8 -> img8
            9 -> img9
            else -> img1 // Caso por defecto
        }
    }

    fun lockImage(){
        val casillas = arrayOf(
            img1, img2, img3,
            img4, img5, img6,
            img7, img8, img9
        )
        for(img in casillas){
            img.isEnabled = false
        }
    }

    fun resetGame(){
        posX = IntArray(5)
        posO = IntArray(5)
        countX = 0
        countO = 0
        winner = ""
        isNext = "x"
        val casillas = arrayOf(
            img1, img2, img3,
            img4, img5, img6,
            img7, img8, img9
        )
        // volver a imagenes iniciales
        for(img in casillas){
            img.setBackgroundResource(R.drawable.ghosty)
            img.isEnabled = true
        }
        imgX.setBackgroundResource(R.drawable.player)
        imgO.setBackgroundResource(0)
        lblVersus.text = resources.getString(R.string.versus)
    }
}