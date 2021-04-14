package com.example.simplecalculator1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.kaen.dagger.ExpressionParser;

public class MainActivity extends AppCompatActivity {

    private TextView inputNumber;
    private TextView outputNumber;
    private ConstraintLayout constraintLayoutFun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUI();
        openContainerFun();
    }

    private void openContainerFun() {
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(constraintLayoutFun, "alpha", 0f, 1f);
        fadeOut.setDuration(600);
        ObjectAnimator fadeOutButton = ObjectAnimator.ofFloat((Button) findViewById(R.id.fab2), "alpha", 0f, 1f);
        fadeOutButton.setDuration(600);

        ObjectAnimator fadeOutButton2 = ObjectAnimator.ofFloat((Button) findViewById(R.id.fab), "alpha", 0f, 1f);
        fadeOutButton2.setDuration(800);

        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(constraintLayoutFun, "alpha", 1f, 0f);
        ObjectAnimator fadeInButton = ObjectAnimator.ofFloat(findViewById(R.id.fab2), "alpha", 1f, 0f);
        fadeInButton.setDuration(450);
        fadeIn.setDuration(600);

        fadeInButton.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                findViewById(R.id.fab2).setVisibility(View.GONE);
                findViewById(R.id.fab).setVisibility(View.VISIBLE);
                fadeOutButton2.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        fadeIn.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                constraintLayoutFun.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        findViewById(R.id.fab).setOnClickListener(v -> {
            findViewById(R.id.fab).setVisibility(View.GONE);
            fadeOut.start();
            constraintLayoutFun.setVisibility(View.VISIBLE);

            fadeOutButton.start();
            findViewById(R.id.fab2).setVisibility(View.VISIBLE);
        });

        findViewById(R.id.fab2).setOnClickListener(v -> {
            fadeIn.start();
            fadeInButton.start();
        });
    }

    private void setupUI() {
        constraintLayoutFun = findViewById(R.id.more_functions_container);
        inputNumber = findViewById(R.id.first_txt);
        outputNumber = findViewById(R.id.second_txt);
        ((TextView) findViewById(R.id.sign_delete_txt)).setOnClickListener((View.OnClickListener) v -> {
            if (!inputNumber.getText().toString().isEmpty()) {
                inputNumber.setText(inputNumber.getText().toString().substring(0, inputNumber.getText().toString().length() - 1));

                realTimeCalculate();
            }
        });
        ((TextView) findViewById(R.id.sign_delete_txt)).setOnLongClickListener(v -> {
            inputNumber.setText("");
            outputNumber.setText("");
            return false;
        });
    }

    public void onClickButton(View view) {
        String string = ((TextView) view).getText().toString();
        inputNumber.setText(inputNumber.getText() + string);

        realTimeCalculate();
        getResultOfDecision();
    }

    public void onClickButtonTrigonometry(View view) {
        String string = ((TextView) view).getText().toString();
        inputNumber.setText(inputNumber.getText() + string + "(");

        realTimeCalculate();
        getResultOfDecision();
    }

    private void getResultOfDecision() {
        findViewById(R.id.sign_equal_txt).setOnClickListener(v -> {
            inputNumber.setText(outputNumber.getText());
            outputNumber.setText("");
        });
        findViewById(R.id.sign_equal_txt).setOnLongClickListener(v -> {
            inputNumber.setText("");
            return true;
        });
    }

    private void realTimeCalculate() {
        try {
            outputNumber.setText(String.valueOf(new ExpressionParser().evaluate(inputNumber.getText().toString(), 9)));
        } catch (Exception e) {
            outputNumber.setText("0");
        }
    }
}