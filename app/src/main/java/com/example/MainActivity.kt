package com.example

import android.annotation.SuppressLint
import android.icu.util.Currency
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.text.Editable
import android.view.LayoutInflater
import com.example.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

abstract class MainActivity : AppCompatActivity() {

    abstract val resId: Int
    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    @SuppressLint("StringFormatInvalid")
    private fun calculateTip() {
        val cost = binding.costOfService.text.toString().toDouble()
        val selectedId:Int =binding.tipOptions.checkedRadioButtonId
        val tipPercentage: Double =when(selectedId){
            R.id.option_ten_percent -> 0.1
            R.id.option_seven_percent -> 0.07
            else -> 0.05
        }
        var tip:Double = cost*tipPercentage
        val roundUp:Boolean = binding.roundTip.isChecked
        if(roundUp ){
            tip = ceil(tip)
        }
        val CurrencyTip:String = NumberFormat.getCurrencyInstance().format(tip)

    }
}