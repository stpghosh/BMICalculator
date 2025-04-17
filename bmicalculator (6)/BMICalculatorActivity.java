package com.example.bmicalculator; // Apna sahi package name

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BMICalculatorActivity extends AppCompatActivity {

    EditText editTextHeight, editTextWeight;
    Button buttonCalculate;
    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calculator);

        editTextHeight = findViewById(R.id.editTextHeight);
        editTextWeight = findViewById(R.id.editTextWeight);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewResult = findViewById(R.id.textViewResult);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        String heightStr = editTextHeight.getText().toString();
        String weightStr = editTextWeight.getText().toString();

        if (heightStr.isEmpty() || weightStr.isEmpty()) {
            Toast.makeText(this, "Please enter both height and weight", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            float heightCm = Float.parseFloat(heightStr);
            float weightKg = Float.parseFloat(weightStr);

            float heightM = heightCm / 100;
            float bmi = weightKg / (heightM * heightM);

            String bmiCategory = getBMICategory(bmi);

            textViewResult.setText(String.format("Your BMI: %.2f\nCategory: %s", bmi, bmiCategory));
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid input! Enter numbers only.", Toast.LENGTH_SHORT).show();
        }
    }

    private String getBMICategory(float bmi) {
        if (bmi < 18.5f) {
            return "Underweight - You should eat more nutritious food.";
        } else if (bmi < 24.9f) {
            return "Normal weight - Great job! Keep maintaining.";
        } else if (bmi < 29.9f) {
            return "Overweight - Try to exercise regularly.";
        } else {
            return "Obese - Consult a doctor for guidance.";
        }
    }
}
