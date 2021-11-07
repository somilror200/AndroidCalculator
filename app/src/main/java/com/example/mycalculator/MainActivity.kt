package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var textInput: TextView? = null
    var lastNumeric: Boolean = false
    var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textInput = findViewById(R.id.textInput)

    }

    fun onDigit(view: View)
    {
        textInput?.append((view as Button).text)
        lastNumeric = true
        lastDot = false
    }

    fun onClear(view: View)
    {
        textInput?.text = ""
    }

    fun onDecimalPoint(view: View)
    {
        if(lastNumeric && !lastDot)
        {
            textInput?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onOperator(view: View)
    {
        textInput?.text?.let{
            if ((lastNumeric || ((textInput?.text == "" || textInput?.text == null) && (view as Button).text == "-")) && !isOperatoradded(it.toString()))
            {
                textInput?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }
    }

    fun isOperatoradded(value: String) : Boolean
    {
        return if(value.startsWith("-"))
        {
            false
        }
        else
        {
            value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")
        }
    }
}