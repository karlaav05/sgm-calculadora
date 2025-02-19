package com.example.proyecto1

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var num1: Double? = null
    private var num2: Double? = null
    private var operator: String? = null

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Configurar la vista principal de la actividad
        setContentView(R.layout.activity_main)

        // Ajustar los paddings según las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Referencia a la pantalla (TextView) de la calculadora
        val pantalla: TextView = findViewById<TextView>(R.id.screen)
        pantalla.text = ""

        // Referencias a los botones de números y operadores
        val btnOne: Button = findViewById<Button>(R.id.one)
        val btnTwo: Button = findViewById<Button>(R.id.two)
        val btnThree: Button = findViewById<Button>(R.id.three)
        val btnFour: Button = findViewById<Button>(R.id.four)
        val btnFive: Button = findViewById<Button>(R.id.five)
        val btnSix: Button = findViewById<Button>(R.id.six)
        val btnSeven: Button = findViewById<Button>(R.id.seven)
        val btnEight: Button = findViewById<Button>(R.id.eight)
        val btnNine: Button = findViewById<Button>(R.id.nueve)
        val btnZero: Button = findViewById<Button>(R.id.zero)
        val btnDot: Button = findViewById<Button>(R.id.dot)

        val btnAddition: Button = findViewById<Button>(R.id.addition)
        val btnSubtraction: Button = findViewById<Button>(R.id.subtraction)
        val btnMultiplication: Button = findViewById<Button>(R.id.multiplication)
        val btnDivision: Button = findViewById<Button>(R.id.division)
        val btnPercentage: Button = findViewById<Button>(R.id.percentage)

        val btnEqual: Button = findViewById<Button>(R.id.equal)
        val btnClear: Button = findViewById<Button>(R.id.Delete)

        // Agregar listeners a los botones de números para que muestren el número en la pantalla
        val numberButtons = listOf(btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine, btnZero, btnDot)
        numberButtons.forEach { button ->
            button.setOnClickListener {
                pantalla.text = pantalla.text.toString() + button.text.toString()
            }
        }

        // Agregar listeners a los botones de operadores
        btnAddition.setOnClickListener { onOperatorClick("+") }
        btnSubtraction.setOnClickListener { onOperatorClick("-") }
        btnMultiplication.setOnClickListener { onOperatorClick("*") }
        btnDivision.setOnClickListener { onOperatorClick("/") }
        btnPercentage.setOnClickListener { onOperatorClick("%") }
        btnEqual.setOnClickListener { onEqualClick() }
        btnClear.setOnClickListener { onClearClick() }
    }

    // Manejar el clic de un operador
    @SuppressLint("SetTextI18n")
    private fun onOperatorClick(op: String) {
        val pantalla: TextView = findViewById<TextView>(R.id.screen)
        if (num1 == null && pantalla.text.isNotBlank() && !pantalla.text.contains("[\\+\\-\\\\\\*\\%]")) {
            num1 = pantalla.text.toString().toDouble()
            operator = op
            pantalla.text = pantalla.text.toString() + op
        }
    }

    // Manejar el clic del botón igual para realizar la operación
    @SuppressLint("SetTextI18n")
    private fun onEqualClick() {
        val pantalla: TextView = findViewById<TextView>(R.id.screen)
        if (operator != null) {
            val numbers = pantalla.text.toString().split(operator!!)
            if (numbers.size == 2 && !numbers.contains("")) {
                num2 = numbers[1].toDouble()
                // Realizar la operación según el operador y mostrar el resultado
                val result = when (operator) {
                    "+" -> num1!! + num2!!
                    "-" -> num1!! - num2!!
                    "*" -> num1!! * num2!!
                    "/" -> num1!! / num2!!
                    "%" -> num1!! % num2!!
                    else -> 0.0
                }
                pantalla.text = result.toString()
                clearValues()
            }
        }
    }

    // Manejar el clic de un operador
    @SuppressLint("SetTextI18n")
    private fun onClearClick() {
        val pantalla: TextView = findViewById<TextView>(R.id.screen)
        pantalla.text = ""
        clearValues()
    }

    private fun clearValues() {
        num1 = null
        num2 = null
        operator = null
    }
}
