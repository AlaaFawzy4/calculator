package com.example.calculator_java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView resultTextView;
    String operation = "";
    String LHS = "";
    boolean isOperatorClick = false;
    boolean isEqualClick = false;
    boolean isDotClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.resultid);
    }
    //5
    public void onDigitClick(View view) {
        if (view instanceof Button) {
            Button button = ((Button) view);

            if (button.getId() == R.id.button_dot) {
                if (isDotClicked) {
                    return;
                } else {
                    isDotClicked = true;
                }
            }

            if (isOperatorClick || isEqualClick) {
                resultTextView.setText(button.getText());
                isEqualClick = false;
                isOperatorClick = false;
            } else {
                resultTextView.append(button.getText());

            }
        }

    }

    public void onOperatorClick(View view) {
        if (view instanceof Button) {
            Button button = ((Button) view);
            //
            isDotClicked = false;
            if (operation.equals("")) {
                LHS = resultTextView.getText().toString();
                operation = button.getText().toString(); //  -
                resultTextView.setText("");
            } else {
                String RHS = resultTextView.getText().toString();//
                if (!RHS.isEmpty()) {
                    LHS = calculate(LHS, operation, RHS);
                    operation = button.getText().toString();
                    resultTextView.setText(LHS);
                    isOperatorClick = true;
                } else {
                    operation = button.getText().toString();
                }
            }
        }
    }

    public void onEqualClick(View view) {
        if (view instanceof Button) {
            String RHS = resultTextView.getText().toString();
            LHS = calculate(LHS, operation, RHS);
            resultTextView.setText(LHS);
            operation = "";
            LHS = "";
            isEqualClick = true;
        }
    }

    private String calculate(String lhs, String operation, String rhs) {
        if (lhs.isEmpty()) {
            return 0.0 + "";
        }
        if (rhs.isEmpty())
            return lhs;
        double left = Double.parseDouble(lhs);
        double right = Double.parseDouble(rhs);
        double result = 0.0;
        if (operation.equals("+")) {
            result = left + right;
        } else if (operation.equals("-")) {
            result = left - right;
        } else if (operation.equals("*")) {
            result = left * right;
        } else if (operation.equals("/")) {
            if (right == 0) {
                Toast.makeText(this, "Can't divide by zero", Toast.LENGTH_LONG).show();
            }

            result = left / right;
        }
        return result + "";
    }

}