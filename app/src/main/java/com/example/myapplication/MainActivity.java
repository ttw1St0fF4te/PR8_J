package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.Normalizer;

public class MainActivity extends AppCompatActivity {

    private TextView Formula, EndResult;
    private Button One, Two, Three, Four, Five, Six, Seven, Eight, Nine, Zero;
    private Button Plus, Dif, Div, Mult, Result;
    private Button Root, Square, Percent, Clear;
    private char Action;
    private double valueFirst = Double.NaN;
    private double valueSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        setupView();
        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                Formula.setText(Formula.getText().toString() + button.getText().toString());
            }
        };
        One.setOnClickListener(numberClickListener);
        Two.setOnClickListener(numberClickListener);
        Three.setOnClickListener(numberClickListener);
        Four.setOnClickListener(numberClickListener);
        Five.setOnClickListener(numberClickListener);
        Six.setOnClickListener(numberClickListener);
        Seven.setOnClickListener(numberClickListener);
        Eight.setOnClickListener(numberClickListener);
        Nine.setOnClickListener(numberClickListener);
        Zero.setOnClickListener(numberClickListener);

        View.OnClickListener actionClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                calculate();
                Action = button.getText().charAt(0);
                Formula.setText(String.valueOf(valueFirst)+Action);
                EndResult.setText(null);
            }
        };
        Plus.setOnClickListener(actionClickListener);
        Div.setOnClickListener(actionClickListener);
        Dif.setOnClickListener(actionClickListener);
        Mult.setOnClickListener(actionClickListener);

        Result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
                Action = '=';
                EndResult.setText(String.valueOf(valueFirst));
                Formula.setText(null);
            }
        });
        Root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Formula.getText().toString().isEmpty()) {
                    double number = Double.parseDouble(Formula.getText().toString());
                    valueFirst = Math.sqrt(number);
                    EndResult.setText(String.valueOf(valueFirst));
                    Formula.setText("");
                }
            }
        });

        Square.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Formula.getText().toString().isEmpty()) {
                    double number = Double.parseDouble(Formula.getText().toString());
                    valueFirst = Math.pow(number, 2);
                    EndResult.setText(String.valueOf(valueFirst));
                    Formula.setText("");
                }
            }
        });

        Percent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Formula.getText().toString().isEmpty()) {
                    double number = Double.parseDouble(Formula.getText().toString());
                    valueFirst = number / 100;
                    EndResult.setText(String.valueOf(valueFirst));
                    Formula.setText("");
                }
            }
        });

        Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Formula.setText("");
                EndResult.setText("0");
                valueFirst = Double.NaN;
            }
        });
    }
    private void setupView() {
        One = (Button) findViewById(R.id.One);
        Two = (Button) findViewById(R.id.Two);
        Three = (Button) findViewById(R.id.Three);
        Four = (Button) findViewById(R.id.Four);
        Five = (Button) findViewById(R.id.Five);
        Six = (Button) findViewById(R.id.Six);
        Seven = (Button) findViewById(R.id.Seven);
        Eight = (Button) findViewById(R.id.Eight);
        Nine = (Button) findViewById(R.id.Nine);
        Zero = (Button) findViewById(R.id.Zero);

        Dif = (Button) findViewById(R.id.Dif);
        Div = (Button) findViewById(R.id.Div);
        Plus = (Button) findViewById(R.id.Plus);
        Mult = (Button) findViewById(R.id.Mult);
        Result = (Button) findViewById(R.id.Result);

        Root = (Button) findViewById(R.id.Root);
        Square = (Button) findViewById(R.id.Square);
        Percent = (Button) findViewById(R.id.Percent);
        Clear = (Button) findViewById(R.id.Clear);

        Formula = (TextView) findViewById(R.id.Formula);
        EndResult = (TextView) findViewById(R.id.EndResult);
    }

    private void calculate() {
        if (!Double.isNaN(valueFirst)) {

            String textFormula = Formula.getText().toString();
            int indexAction = textFormula.indexOf(Action);

            if (indexAction != -1) {
                String numberValue = textFormula.substring(indexAction + 1);
                valueSecond = Double.parseDouble(numberValue);

                switch (Action) {
                    case '/':
                        if (valueSecond == 0){
                            valueFirst = 0.0;
                        }
                        else {
                            valueFirst /= valueSecond;
                        }
                        break;

                    case '*':
                        valueFirst *= valueSecond;
                        break;

                    case '+':
                        valueFirst += valueSecond;
                        break;

                    case '-':
                        valueFirst -= valueSecond;
                        break;

                    case '=':
                        valueFirst = valueSecond;
                        break;
                }
            }
        }
        else {
            valueFirst = Double.parseDouble(Formula.getText().toString());
        }
        EndResult.setText(String.valueOf(valueFirst));
        Formula.setText("");
    }
}