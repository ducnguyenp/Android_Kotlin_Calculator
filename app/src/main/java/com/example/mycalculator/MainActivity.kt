package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var tvInputCal: TextView? = null
    private var lastNumeric: Boolean = false
    private var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInputCal = findViewById(R.id.tv_input_cal)
    }

    fun onDigit(view: View) {
        // TODO right now view is the button that we add the fun in the click of that button
        tvInputCal?.append((view as Button).text)
        lastNumeric = true
        lastDot = true
    }

    fun onClear(view: View) {
        tvInputCal?.text = ""
    }

    fun onDecimalPoint(view: View) {
        if (lastNumeric && !lastDot) {
            tvInputCal?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onEqual(view: View) {
        if (lastNumeric) {
            var tvValue = tvInputCal?.text.toString()
            var prefix = ""
            try {
                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("-")) {
                    var splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    var result = (one.toDouble() - two.toDouble()).toString()
                    tvInputCal?.text = removeZeroAfterDot(result)
                } else  if (tvValue.contains("+")) {
                    var splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    var result = (one.toDouble() + two.toDouble()).toString()
                    tvInputCal?.text = removeZeroAfterDot(result)
                } else  if (tvValue.contains("/")) {
                    var splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    var result = (one.toDouble() / two.toDouble()).toString()
                    tvInputCal?.text = removeZeroAfterDot(result)
                } else  if (tvValue.contains("*")) {
                    var splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    var result = (one.toDouble() * two.toDouble()).toString()
                    tvInputCal?.text = removeZeroAfterDot(result)
                }
            }catch (e: java.lang.ArithmeticException) {
                e.printStackTrace()
            }

        }

    }
    fun onOperator(view: View) {
        tvInputCal?.text.let {
            if (lastNumeric && !isOperatorAdded(it.toString())) {
                tvInputCal?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }
    }

    private fun removeZeroAfterDot(result: String) : String {
        var value = result
        if (result.contains(".0")) {
            value = result.substring(0, result.length - 2)
        }
        return value
    }

    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
        }
    }
}