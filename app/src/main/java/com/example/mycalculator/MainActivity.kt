package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

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

    fun onEqual(view: View)
    {
        if(lastNumeric)
        {
            var textValue = textInput?.text.toString()
            var prefix = ""
            try
            {
                if(textValue.startsWith("-"))
                {
                    prefix = "-"
                    textValue = textValue.substring(1)
                }

                if(textValue.contains("-"))
                {
                    val splitValue = textValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prefix.isNotEmpty())
                    {
                        one = prefix + one
                    }

                    textInput?.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                }
                else if(textValue.contains("+"))
                {
                    val splitValue = textValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prefix.isNotEmpty())
                    {
                        one = prefix + one
                    }

                    textInput?.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                }
                else if(textValue.contains("/"))
                {
                    val splitValue = textValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prefix.isNotEmpty())
                    {
                        one = prefix + one
                    }

                    textInput?.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                }
                else if(textValue.contains("*"))
                {
                    val splitValue = textValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prefix.isNotEmpty())
                    {
                        one = prefix + one
                    }

                    textInput?.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                }

            }
            catch(e: ArithmeticException)
            {
                e.printStackTrace()
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
    private fun removeZeroAfterDot(result: String) : String
    {
        var value = result
        if(result.contains(".0"))
            value = result.substring(0, result.length - 2)
        return value
    }
}