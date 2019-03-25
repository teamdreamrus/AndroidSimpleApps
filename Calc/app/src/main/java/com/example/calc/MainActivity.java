package com.example.calc;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;


import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private TextView _screen;
    private String display = "";
    public String operation = "";
    private String Result = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _screen = (TextView)findViewById(R.id.textView);
        _screen.setText(display);

    }
    private void updateScreen(){
        _screen.setText(display);
    }
    public void OnClickNumber(View v){
        if(!Result.equals("")){
            clear();
            updateScreen();
        }
        Button b = (Button) v;
        display+=b.getText();
        updateScreen();
    }
    private boolean isOperator(char op){
        switch (op){
            case '+':
            case '-':
            case '*':
            case '/': return true;
            default: return false;
        }
    }
    public void onClickDot(View v){
        Button b = (Button) v;
        String[] args = display.split(Pattern.quote(operation));
        if(!display.equals(""))
            if(!args[args.length-1].contains(".")){
                display += b.getText();
                updateScreen();
            }


    }
    public void onClickBackSpace(View v){
        if(!display.equals("")) {
            if(display.substring(display.length()-1 ).equals(Pattern.quote(operation)))
                operation="";
            display = display.substring(0,display.length()-1);
            updateScreen();
        }else {
            clear();
            updateScreen();
        }


    }
    public void onClickOperation(View v) {
        Button b = (Button) v;
        switch (display) {
            case "-":
            case "+":
            case "/":
            case "*":
                return;
            default:
                break;
        }

        if (display.equals(""))
            {
            switch (b.getText().toString()) {
                case "-": {
                    display += b.getText();
                    updateScreen();
                    return;
                }
                case "+":
                case "/":
                case "*":
                    return;
                default:
                    break;
            }
        }else {
            if(display.substring(display.length()-1).equals(".")) return;
                if (!Result.equals("")) {
                String _display = Result;
                clear();
                display = _display;
            }

            if (!operation.equals("")) {
                if (isOperator(display.charAt(display.length() - 1))) {
                    display = display.replace(display.charAt(display.length() - 1), b.getText().charAt(0));
                    updateScreen();
                    return;
                } else {
                    getResult();
                    display = Result;
                    Result = "";
                }
                operation = b.getText().toString();
            }


            display += b.getText();
            operation = b.getText().toString();
            updateScreen();
        }
    }
    protected void clear(){
        display = "";
        operation = "";
        Result = "";
    }

    public void onClickClear(View v){
        clear();
        updateScreen();

    }

    private double operate(String a, String b, String op){
        switch (op){
            case "+": return (Double.valueOf(a) + Double.valueOf(b));
            case "-": return (Double.valueOf(a) - Double.valueOf(b));
            case "*": return (Double.valueOf(a) * Double.valueOf(b));
            case "/": try {
                return (Double.valueOf(a) / Double.valueOf(b));
            }catch (Exception e){

            }

            default: return -1;
        }

    }
    private boolean getResult(){
        if(operation.equals("")) return false;
        String[] args = display.split(Pattern.quote(operation));
        if(args.length < 2) return false;
        if(args.length > 2) Result = String.valueOf(operate("-"+args[1],args[2],operation));
        else Result = String.valueOf(operate(args[0],args[1],operation));
        return true;
    }

    public void onClickEqual(View v){
        if(display.equals("")) return;
       if(!getResult()) return;
        _screen.setText(display + "\n" + String.valueOf(Result));
    }

}
