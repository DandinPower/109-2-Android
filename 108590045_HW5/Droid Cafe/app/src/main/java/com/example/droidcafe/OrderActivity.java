package com.example.droidcafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {
    private TextView mShowText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        mShowText = findViewById(R.id.textView);
        Intent intent = getIntent();
        String message = "Order: "+ intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        mShowText.setText(message);
    }
}