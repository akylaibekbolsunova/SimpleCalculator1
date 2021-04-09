package com.example.simplecalculator1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import io.kaen.dagger.ExpressionParser;

public class MainActivity extends AppCompatActivity {

    private TextView inputNumber;
    private TextView outputNumber;
    private GridLayout gridLayout;
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridLayout = findViewById(R.id.grid_layout_for_numbers);
        setupUI();
        openFragment();
    }

    private void openFragment() {
//        findViewById(R.id.fab).setOnClickListener(v -> {
//            FragmentManager manager = getSupportFragmentManager();
//            FragmentTransaction transaction = manager.beginTransaction().add(R.id.more_func,new OtherFunctionFragment());
//            transaction.commit();
//            gridLayout.setOnClickListener(v1 -> {
//                FragmentTransaction transaction1 = manager.beginTransaction().hide(new OtherFunctionFragment());
//                transaction1.commit();
//            });
//            constraintLayout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.e("ololo", "clicked");
//                    FragmentTransaction transaction1 = manager.beginTransaction().hide(new OtherFunctionFragment());
//                    transaction1.commit();
//                }
//            });
//        });
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
        getResultOfDecision();


    }

    private void getResultOfDecision() {
        findViewById(R.id.sign_equal_txt).setOnClickListener(v -> {
            inputNumber.setText(outputNumber.getText());
            outputNumber.setText("");

        });
    }

    private void realTimeCalculate() {
        try {
            outputNumber.setText(String.valueOf(new ExpressionParser().evaluate(inputNumber.getText().toString(), 3)));
        } catch (Exception e) {
            outputNumber.setText("0");
        }
    }
}