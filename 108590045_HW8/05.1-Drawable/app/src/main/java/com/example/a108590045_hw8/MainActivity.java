package com.example.a108590045_hw8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView battery;
    private int level = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        battery = findViewById(R.id.imageView2);
        battery.setImageResource(R.drawable.levelllll);

    }

    public void minus(View view) {
        if(level >0){
            level --;
        }
        battery.setImageLevel(level);
    }

    public void add(View view) {
        if(level <6){
            level ++;
        }
        battery.setImageLevel(level);
    }
}