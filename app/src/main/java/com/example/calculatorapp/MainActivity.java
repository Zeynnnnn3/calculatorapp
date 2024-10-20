package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText display;
    private double firstNumber = 0.0;
    private double secondNumber = 0.0;
    private String operator = "";
    private boolean isOperatorPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                if (isOperatorPressed) {
                    display.setText(b.getText().toString());
                    isOperatorPressed = false;
                } else {
                    display.append(b.getText().toString());
                }
            }
        };

        findViewById(R.id.btn0).setOnClickListener(numberClickListener);
        findViewById(R.id.btn1).setOnClickListener(numberClickListener);
        findViewById(R.id.btn2).setOnClickListener(numberClickListener);
        findViewById(R.id.btn3).setOnClickListener(numberClickListener);
        findViewById(R.id.btn4).setOnClickListener(numberClickListener);
        findViewById(R.id.btn5).setOnClickListener(numberClickListener);
        findViewById(R.id.btn6).setOnClickListener(numberClickListener);
        findViewById(R.id.btn7).setOnClickListener(numberClickListener);
        findViewById(R.id.btn8).setOnClickListener(numberClickListener);
        findViewById(R.id.btn9).setOnClickListener(numberClickListener);

        findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOperator("+");
            }
        });
        findViewById(R.id.btnSubtract).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOperator("-");
            }
        });
        findViewById(R.id.btnMultiply).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOperator("*");
            }
        });
        findViewById(R.id.btnDivide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOperator("/");
            }
        });

        findViewById(R.id.btnEqual).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!operator.isEmpty()) {
                    secondNumber = Double.parseDouble(display.getText().toString());
                    double result = calculateResult();
                    display.setText(String.valueOf(result));
                    operator = "";
                    isOperatorPressed = true;
                }
            }
        });

        findViewById(R.id.btnClear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.setText("");
                firstNumber = 0.0;
                secondNumber = 0.0;
                operator = "";
            }
        });

        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = display.getText().toString();
                if (text.length() > 0) {
                    display.setText(text.substring(0, text.length() - 1));
                }
            }
        });

        findViewById(R.id.btnSignChange).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = display.getText().toString();
                if (!text.isEmpty()) {
                    double value = Double.parseDouble(text);
                    display.setText(String.valueOf(value * -1));
                }
            }
        });

        findViewById(R.id.btnSquareRoot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double value = Double.parseDouble(display.getText().toString());
                if (value >= 0) {
                    display.setText(String.valueOf(Math.sqrt(value)));
                } else {
                    display.setText("Error");
                }
            }
        });
    }

    private void handleOperator(String op) {
        firstNumber = Double.parseDouble(display.getText().toString());
        operator = op;
        isOperatorPressed = true;
    }

    private double calculateResult() {
        switch (operator) {
            case "+":
                return firstNumber + secondNumber;
            case "-":
                return firstNumber - secondNumber;
            case "*":
                return firstNumber * secondNumber;
            case "/":
                if (secondNumber != 0) {
                    return firstNumber / secondNumber;
                }
                return 0.0;
            default:
                return 0.0;
        }
    }
}
