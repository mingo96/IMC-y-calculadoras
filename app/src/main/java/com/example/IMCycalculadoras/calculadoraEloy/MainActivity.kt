package com.example.IMCycalculadoras.calculadoraEloy

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.IMCycalculadoras.R


class CalculadoraEloy : AppCompatActivity() {

    /**
     * @property calculation genera un objeto de tipo Calculation para efectuar las operaciones y gestiones
     * */
    val calculation = Calculation()

    /**
     * @property buttons contiene todos los botones, que son basicamente lo unico que necesitamos
     * para el funcionamiento basico
     * */
    lateinit var buttons : Array<Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.xml_eloy)

        start()

        //forzamos el tema de la app a claro, porque hay dispositivos (el mio) que lo fuerzan a
        //oscuro y queda mal

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

    }


    /**
     * inicia [buttons] y crea los setOnClickListeners con lo que hacen (darle la cadena del
     * boton a [calculation] y actualizar el texto en pantalla)
     * */
    fun start(){
        buttons = arrayOf<Button>(findViewById(R.id.button0),
            findViewById(R.id.button1),
            findViewById(R.id.button2),
            findViewById(R.id.button3),
            findViewById(R.id.button4),
            findViewById(R.id.button5),
            findViewById(R.id.button6),
            findViewById(R.id.button7),
            findViewById(R.id.button8),
            findViewById(R.id.button9),
            findViewById(R.id.calculate),
            findViewById(R.id.clear),
            findViewById(R.id.divide),
            findViewById(R.id.minus),
            findViewById(R.id.sum),
            findViewById(R.id.times),
            findViewById(R.id.calculate),
            findViewById(R.id.buttonErase),
            findViewById(R.id.buttonDot))

        for (button in buttons){

            button.setOnClickListener {
                calculation.screenChange(button.text.toString())
                findViewById<TextView>(R.id.screen_text).text = calculation.returnActualText()
            }

        }
    }

}