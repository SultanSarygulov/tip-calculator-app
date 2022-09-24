package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    private fun calculateTip(){
        //Достаем введеный текст и переводим в Double
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if (cost == null){
            binding.tipAmount.text = ""
            return
        }

        //Находим выбраный процент чаевых, из чего делаем сумму чаевых
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.amazing_button -> 0.20
            R.id.good_button -> 0.18
            else -> 0.15
        }
        var tip = cost * tipPercentage

        // Проверяем надо ли округлять чаевые
        if (binding.roundUpSwitch.isChecked){
            tip = ceil(tip)
        }

        NumberFormat.getCurrencyInstance()
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        binding.tipAmount.text = getString(R.string.tip_amount, formattedTip)
    }
}