package com.example.unit_conv;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import java.text.DecimalFormat;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextAmount;
    private Spinner spinnerFrom;
    private Spinner spinnerTo;
    private Button buttonConvert;
    private TextView textViewResult;

    private String[] currencies = {"USD", "EUR", "GBP", "JPY", "CAD", "INR"};
    private double[] conversionRates = {1.0, 0.86, 0.73, 109.71, 1.23, 82.03};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextAmount = findViewById(R.id.editTextAmount);
        spinnerFrom = findViewById(R.id.spinnerFrom);
        spinnerTo = findViewById(R.id.spinnerTo);
        buttonConvert = findViewById(R.id.buttonConvert);
        textViewResult = findViewById(R.id.textViewResult);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, currencies);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);
    }

    public void onConvertClick(View view) {
        String amountString = editTextAmount.getText().toString();
        if (amountString.isEmpty()) {
            textViewResult.setText("Please enter an amount");
            return;
        }

        double amount = Double.parseDouble(amountString);
        int fromIndex = spinnerFrom.getSelectedItemPosition();
        int toIndex = spinnerTo.getSelectedItemPosition();

        double fromRate = conversionRates[fromIndex];
        double toRate = conversionRates[toIndex];

        double convertedAmount = (amount / fromRate) * toRate;

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String result = decimalFormat.format(convertedAmount);

        textViewResult.setText(result);
    }
}