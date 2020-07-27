package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class End_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_page);
        Intent intent=getIntent();
        int ans= (int) getIntent().getExtras().get("Total");
        TextView textView=findViewById(R.id.answerDis);
        textView.setText("Total number of correct answer is "+String.valueOf(5-ans));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.exit(0);
    }
}
