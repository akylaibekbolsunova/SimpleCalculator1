package com.example.simplecalculator1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import io.kaen.dagger.ExpressionParser;

public class MainActivity extends AppCompatActivity {

    private TextView inputNumber;
    private TextView outputNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUI();
    }


    private void setupUI() {
        inputNumber = findViewById(R.id.first_txt);
        outputNumber = findViewById(R.id.second_txt);

        ((TextView) findViewById(R.id.sign_delete_txt)).setOnClickListener((View.OnClickListener) v -> {
            inputNumber.setText(inputNumber.getText().toString().substring(0, inputNumber.getText().toString().length() - 1));

            realTimeCalculate();
        });
    }

    public void onClickButton(View view) {
        String string = ((TextView) view).getText().toString();
        inputNumber.setText(inputNumber.getText() + string);

        realTimeCalculate();
    }

    private void realTimeCalculate() {
        try {
            outputNumber.setText(String.valueOf(new ExpressionParser().evaluate(inputNumber.getText().toString(), 3)));
        } catch (Exception e) {
            outputNumber.setText("0");
        }
    }
}