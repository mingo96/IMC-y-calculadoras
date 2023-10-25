package com.example.IMCycalculadoras

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.compose.setContent
import com.example.IMCycalculadoras.R
import com.example.IMCycalculadoras.calculadoraEloy.CalculadoraEloy
import com.example.IMCycalculadoras.calculadoraIMC.CalculadoraIMC
import com.example.IMCycalculadoras.calculadoraJavi.CalculadoraJavi
class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        initButtons()
    }

    fun initButtons() {
        findViewById<Button>(R.id.button_activityIMC).setOnClickListener {
            startActivity(Intent(this, CalculadoraIMC::class.java))
        }
        findViewById<Button>(R.id.button_activityEloy).setOnClickListener{
            val intent = Intent(this,CalculadoraEloy::class.java)
            startActivity(intent)
        }
       /* findViewById<Button>(R.id.button_activityJosema).setOnClickListener{
            startActivity(Intent(this,CalculadoraJosema::class.java))
        }
        */
        findViewById<Button>(R.id.button_activityJavi).setOnClickListener{
            startActivity(Intent(this,CalculadoraJavi::class.java))
        }
    }


    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}
