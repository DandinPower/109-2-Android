package com.example.checkboxes_and_etc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private CheckBox ChocolateBox;
    private CheckBox SprinklesBox;
    private CheckBox CrushedBox;
    private CheckBox CherriesBox;
    private CheckBox OrioBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ChocolateBox = (CheckBox) findViewById(R.id.chocolate_box);
        SprinklesBox = (CheckBox) findViewById(R.id.sprinkles_box);
        CrushedBox = (CheckBox) findViewById(R.id.crushed_box);
        CherriesBox = (CheckBox) findViewById(R.id.cherries_box);
        OrioBox = (CheckBox) findViewById(R.id.orio_box);

    }
    public void displayToast(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }
    public void ShowMessage(View view) {
        String mToast = "Toppings:";
        if(ChocolateBox.isChecked()){
            mToast = mToast + " " + getString(R.string.chocolate_syrup);
        }
        if(SprinklesBox.isChecked()){
            mToast = mToast + " " + getString(R.string.sprinkles);
        }
        if(CrushedBox.isChecked()){
            mToast = mToast + " " + getString(R.string.crushed_nuts);
        }
        if(CherriesBox.isChecked()){
            mToast = mToast + " " + getString(R.string.cherries);
        }
        if(OrioBox.isChecked()){
            mToast = mToast + " " + getString(R.string.orio_cookie_crumbles);
        }
        displayToast(mToast);
    }
}