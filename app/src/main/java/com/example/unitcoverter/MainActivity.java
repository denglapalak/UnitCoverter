package com.example.unitcoverter;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText inputValue;
    private Spinner inputUnitSpinner;
    private Spinner outputUnitSpinner;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        inputValue = findViewById(R.id.inputValue);
        inputUnitSpinner = findViewById(R.id.inputUnitSpinner);
        outputUnitSpinner = findViewById(R.id.outputUnitSpinner);
        resultText = findViewById(R.id.resultText);

        // Populate spinners with unit options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.unit_options,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputUnitSpinner.setAdapter(adapter);
        outputUnitSpinner.setAdapter(adapter);

        // Set up listener for conversion button
        findViewById(R.id.convertButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertUnits();
            }
        });
    }

    private void convertUnits() {
        // Retrieve user input
        double inputValue = Double.parseDouble(this.inputValue.getText().toString());
        String inputUnit = inputUnitSpinner.getSelectedItem().toString();
        String outputUnit = outputUnitSpinner.getSelectedItem().toString();

        // Perform the conversion
        double result = performConversion(inputValue, inputUnit, outputUnit);

        // Display the result
        resultText.setText(String.valueOf(result));
    }

    private double performConversion(double value, String fromUnit, String toUnit) {
        // Conversion logic for centimeters and meters
        if (fromUnit.equals("Centimeters") && toUnit.equals("Meters")) {
            return value / 100; // 1 meter = 100 centimeters
        } else if (fromUnit.equals("Meters") && toUnit.equals("Centimeters")) {
            return value * 100; // Reverse conversion
        }

        // Conversion logic for grams and kilograms
        if (fromUnit.equals("Grams") && toUnit.equals("Kilograms")) {
            return value / 1000; // 1 kilogram = 1000 grams
        } else if (fromUnit.equals("Kilograms") && toUnit.equals("Grams")) {
            return value * 1000; // Reverse conversion
        }

        // Add more conversion logic for other units as needed

        return 0; // Default value
    }

}
