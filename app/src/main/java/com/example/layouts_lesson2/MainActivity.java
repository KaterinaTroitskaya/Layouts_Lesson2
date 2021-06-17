package com.example.layouts_lesson2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    private Calculator calc;
    private EditText text;
    static String operator = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calc = new Calculator();
        initView();
    }

    private void initView(){
        text = findViewById(R.id.key_result);

        initButtonsClickListener();
    }

    private void initButtonsClickListener(){
        Button button1 = findViewById(R.id.button_1);
        Button button2 = findViewById(R.id.button_2);
        Button button3 = findViewById(R.id.button_3);
        Button button4 = findViewById(R.id.button_4);
        Button button5 = findViewById(R.id.button_5);
        Button button6 = findViewById(R.id.button_6);
        Button button7 = findViewById(R.id.button_7);
        Button button8 = findViewById(R.id.button_8);
        Button button9 = findViewById(R.id.button_9);
        Button button0 = findViewById(R.id.button_0);
        Button buttonAdd = findViewById(R.id.button_addition);
        Button buttonDiv = findViewById(R.id.button_division);
        Button buttonSub = findViewById(R.id.button_substraction);
        Button buttonMulti = findViewById(R.id.button_multiplication);
        Button buttonPoint = findViewById(R.id.button_point);
        Button buttonEquals = findViewById(R.id.button_equalsign);
        Button buttonClear = findViewById(R.id.button_clear);

        button1.setOnClickListener(numberButtonsClickListener);
        button2.setOnClickListener(numberButtonsClickListener);
        button3.setOnClickListener(numberButtonsClickListener);
        button4.setOnClickListener(numberButtonsClickListener);
        button5.setOnClickListener(numberButtonsClickListener);
        button6.setOnClickListener(numberButtonsClickListener);
        button7.setOnClickListener(numberButtonsClickListener);
        button8.setOnClickListener(numberButtonsClickListener);
        button9.setOnClickListener(numberButtonsClickListener);
        button0.setOnClickListener(numberButtonsClickListener);
        buttonAdd.setOnClickListener(operatorButtonAddClickListener);
        buttonDiv.setOnClickListener(operatorButtonDivClickListener);
        buttonSub.setOnClickListener(operatorButtonSubClickListener);
        buttonMulti.setOnClickListener(operatorButtonMultiClickListener);
        buttonPoint.setOnClickListener(pointButtonClickListener);
        buttonEquals.setOnClickListener(equalButtonsClickListener);
        buttonClear.setOnClickListener(clearButtonsClickListener);
    }
    public View.OnClickListener numberButtonsClickListener;
    {
        numberButtonsClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText tv = (EditText) v;
                String textFromTV = tv.getText().toString();
                text.append(textFromTV);
            }
        };
    }
    public View.OnClickListener clearButtonsClickListener;
    {
        clearButtonsClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText("");
                calc.number2="";
                calc.number1="";
                calc.result = 0;
            }
        };
    }
    public View.OnClickListener pointButtonClickListener;
    {
        pointButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = text.getText().toString().trim();
                if(str.length() == 0) return;
                str = str.substring(0,str.length()-1);
                text.setText(str);
            }
        };
    }
    public View.OnClickListener operatorButtonAddClickListener = v -> setOperator("+");

    public View.OnClickListener operatorButtonSubClickListener = v -> setOperator("-");

    public View.OnClickListener operatorButtonDivClickListener = v -> setOperator("/");

    public View.OnClickListener operatorButtonMultiClickListener = v -> setOperator("*");

    public View.OnClickListener equalButtonsClickListener = v -> Equals();

    public void setOperator(String _operator) {
        if (calc.number1.equals("")) {
            calc.number1 = text.getText().toString().trim();
        } else {
            calc.number2 = text.getText().toString().trim();
            calc.number1 = String.valueOf(calc.result);
        }
        operator = _operator;
        text.setText("");

    }

    public void Equals() {
        String str = text.getText().toString().trim();
        if (str.length() == 0) return;

        calc.number2 = text.getText().toString().trim();
        if (calc.number1.equals("")) return;


        switch (operator) {
            case "+":
                calc.Sum();
                break;

            case "-":
                calc.Min();
                break;

            case "/":
                calc.Div();

                break;

            case "*":
                calc.Mult();
                break;

            default:
                calc.result = 0;
        }

        text.setText("" + calc.result);

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("calc", calc);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        calc = savedInstanceState.getParcelable("calc");
        text = findViewById(R.id.key_result);
        text.setText(String.valueOf(calc.result));
    }
}

