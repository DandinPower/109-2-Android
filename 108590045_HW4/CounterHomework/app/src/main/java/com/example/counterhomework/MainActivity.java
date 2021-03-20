package com.example.counterhomework;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int count = 0;
    private TextView CountShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CountShow = (TextView) findViewById(R.id.CountShow);
        if(savedInstanceState != null){
            CountShow.setText(savedInstanceState.getString("Count_Text"));
            count = savedInstanceState.getInt("Count");
        }
    }

    public void onCount(View view){
        count++;
        if(CountShow != null){
            CountShow.setText(Integer.toString(count));
        }
    }

    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        if(CountShow != null){
            outState.putString("Count_Text",CountShow.getText().toString());
        }
        if(count != 0){
            outState.putInt("Count",count);
        }
    }
}