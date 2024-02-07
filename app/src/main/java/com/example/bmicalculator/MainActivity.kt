package com.example.bmicalculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.internal.ViewUtils.hideKeyboard

class MainActivity : AppCompatActivity() {

    //    Toast function
    private var currToast : Toast? =null
    fun showToast(message: String){
        currToast?.cancel()
        currToast= Toast.makeText(this, message, Toast.LENGTH_SHORT)
        currToast?.show()
    }
    // hide keyboard
    private fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // bmi fields
        var bmi_height_field=findViewById<EditText>(R.id.bmi_height_field)
        var bmi_weight_field=findViewById<EditText>(R.id.bmi_weight_field)
        var calculate_bmi_btn=findViewById<Button>(R.id.calculate_bmi_btn)
        var bmi_result_field=findViewById<TextView>(R.id.bmi_result)
        var reser_bmi_btn=findViewById<Button>(R.id.reset_bmi_btn)

        calculate_bmi_btn.setOnClickListener{
            hideKeyboard()
            var weight=bmi_weight_field.text.toString()
            var height=bmi_height_field.text.toString()
            if(weight.length==0){
                showToast("Please enter weight")
            }else if(height.length==0){
                showToast(("Please enter height"))
            }else{
                var heightInMeters:Float=height.toFloat() / 100
                var bmi:Float=weight.toFloat() / (heightInMeters * heightInMeters)
                var finalBmi=String.format("%.2f", bmi)
                val type = when {
                    bmi < 18.5f -> "Under Weight"
                    bmi > 19f && bmi < 24.9f -> "Healthy"
                    bmi > 25f && bmi < 29.9f -> "Over Weight"
                    else -> "Obesity"
                }
                bmi_result_field.setText(String.format("%.2f", bmi))
                showToast("BMI : $finalBmi, $type")
            }
        }

        reser_bmi_btn.setOnClickListener{
            bmi_weight_field.setText("")
            bmi_height_field.setText("")
            bmi_result_field.setText("00.00")
        }
    }
}