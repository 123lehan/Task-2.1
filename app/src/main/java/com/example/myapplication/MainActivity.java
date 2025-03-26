package com.example.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Spinner sourceUnitSpinner, destinationUnitSpinner;
    EditText valueToConvert;
    Button convertButton;
    TextView convertedValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize the Spinners
        sourceUnitSpinner = findViewById(R.id.sourceUnitSpinner);
        destinationUnitSpinner = findViewById(R.id.destinationUnitSpinner);
        valueToConvert = findViewById(R.id.valueToConvert);
        convertButton = findViewById(R.id.convertButton);
        convertedValue = findViewById(R.id.convertedValue);



        // Initialize the adapter with the combined 'all_units' array
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.all_units, android.R.layout.simple_spinner_item);

        // Set the adapter for both spinners
        sourceUnitSpinner.setAdapter(adapter);
        destinationUnitSpinner.setAdapter(adapter);

        // Set onClickListener for the Convert Button
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the value from the EditText
                String inputValue = valueToConvert.getText().toString();

                if (!inputValue.isEmpty()) {
                    // Get the selected units
                    String sourceUnit = sourceUnitSpinner.getSelectedItem().toString();
                    String destinationUnit = destinationUnitSpinner.getSelectedItem().toString();

                    // Convert the input value
                    double value = Double.parseDouble(inputValue);
                    double converted = convertValue(sourceUnit, destinationUnit, value);

                    // Display the converted value in the TextView
                    convertedValue.setText("Converted value: " + converted);
                } else {
                    convertedValue.setText("Please enter a value.");
                }
            }
        });
    }

    /**
     * This function performs the conversion based on the selected units and the input value.
     */
    public double convertValue(String sourceUnit, String destinationUnit, double value) {
        // Length Conversions
        if (sourceUnit.equals("Meters") && destinationUnit.equals("Kilometers")) {
            return value / 1000; // Meters to Kilometers
        } else if (sourceUnit.equals("Kilometers") && destinationUnit.equals("Meters")) {
            return value * 1000; // Kilometers to Meters
        } else if (sourceUnit.equals("Inches") && destinationUnit.equals("Centimeters")) {
            return value * 2.54; // Inches to Centimeters
        } else if (sourceUnit.equals("Feet") && destinationUnit.equals("Centimeters")) {
            return value * 30.48; // Feet to Centimeters
        } else if (sourceUnit.equals("Yards") && destinationUnit.equals("Centimeters")) {
            return value * 91.44; // Yards to Centimeters
        } else if (sourceUnit.equals("Miles") && destinationUnit.equals("Kilometers")) {
            return value * 1.60934; // Miles to Kilometers
        }

        // Weight Conversions
        if (sourceUnit.equals("Pounds") && destinationUnit.equals("Kilograms")) {
            return value * 0.453592; // Pounds to Kilograms
        } else if (sourceUnit.equals("Ounces") && destinationUnit.equals("Grams")) {
            return value * 28.3495; // Ounces to Grams
        } else if (sourceUnit.equals("Tons") && destinationUnit.equals("Kilograms")) {
            return value * 907.185; // Tons to Kilograms
        }

        // Temperature Conversions
        if (sourceUnit.equals("Celsius") && destinationUnit.equals("Fahrenheit")) {
            return (value * 1.8) + 32; // Celsius to Fahrenheit
        } else if (sourceUnit.equals("Fahrenheit") && destinationUnit.equals("Celsius")) {
            return (value - 32) / 1.8; // Fahrenheit to Celsius
        } else if (sourceUnit.equals("Celsius") && destinationUnit.equals("Kelvin")) {
            return value + 273.15; // Celsius to Kelvin
        } else if (sourceUnit.equals("Kelvin") && destinationUnit.equals("Celsius")) {
            return value - 273.15; // Kelvin to Celsius
        }

        // If no conversion match is found, return the original value
        return value;

    }
}