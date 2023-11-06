package com.android.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.android.bmicalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.WeightPicker.minValue = 30
        binding.WeightPicker.maxValue = 150


        binding.heightPicker.minValue = 100
        binding.heightPicker.maxValue = 250

        binding.WeightPicker.setOnValueChangedListener { _, _, _ ->
            calculateBMI()
        }

        binding.heightPicker.setOnValueChangedListener { _, _, _ ->
            calculateBMI()
        }
    }

    private fun calculateBMI() {
        val height = binding.heightPicker.value
        val doubleHeight = height.toDouble() / 100

        val weight = binding.WeightPicker.value
        val bmi = weight.toDouble() / (doubleHeight * doubleHeight)

        binding.resultsTv.text = String.format("Your BMI is: %.2f", bmi)
        binding.healthyTv.text = String.format("Considered: %s", healthyMessage(bmi))

    }

    private fun healthyMessage(bmi: Double): String {
        if (bmi < 18.5)
            return "Underweight"
        if (bmi < 25.0)
            return "Healthy"
        if (bmi < 30.0)
            return "Overweight"
        return "Obese"
    }
}