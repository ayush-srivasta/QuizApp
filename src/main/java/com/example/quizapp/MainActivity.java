package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
      Button button1, button2, button3, button4,submit,b5,next;

    int count=0,cl=1,correct=0;
    int fi=0;
    int que=1;
    Cursor cursor;
    Boolean sumbit_button=true;
    String question,answer1,answer2,answer3,answer4,correctop;
    TextView textView,questin;



   private Boolean clicked=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
ConstraintLayout constraintLayout=findViewById(R.id.layout);
AnimationDrawable animationDrawable=(AnimationDrawable) constraintLayout.getBackground();
animationDrawable.setEnterFadeDuration(200);
animationDrawable.setExitFadeDuration(400);
animationDrawable.start();

        textView=findViewById(R.id.textView);
        textView.setText("Question no:"+Integer.toString(que));
        button1=findViewById(R.id.option1);
        button1.setOnClickListener(this);
        button2=findViewById(R.id.option2);
        button2.setOnClickListener(this);
        button3=findViewById(R.id.option3);
        button3.setOnClickListener(this);
        button4=findViewById(R.id.option4);
        button4.setOnClickListener(this);
        submit=findViewById(R.id.submit);
       questin=findViewById(R.id.question);

        SQLiteOpenHelper dataBase=new Database(this);


        try {
            SQLiteDatabase db = dataBase.getReadableDatabase();
             cursor = db.query("DRINK",
                    new String[]{"_id", "QUESTION", "OPTION1", "OPTION2", "OPTION3", "OPTION4","RESULT"}, null, null, null, null, null);
            cursor.moveToFirst();

          questin.setText(cursor.getString(1));
          button1.setText(cursor.getString(2));
          button2.setText(cursor.getString(3));
          button3.setText(cursor.getString(4));
          button4.setText(cursor.getString(5));
          correctop=cursor.getString(6);

        }
        catch (SQLException e)
        {
            Toast toast=Toast.makeText(this,"Data base unavilable",Toast.LENGTH_SHORT);
            toast.show();
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sumbit_button=false;
                if(fi!=0){
                b5=findViewById(fi);
                b5.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                if(b5.getText().toString().equals(correctop))
                    b5.setBackgroundColor(getResources().getColor(R.color.green));
                else
                    if(button1.getText().toString().equals(correctop))
                       {button1.setBackgroundColor(getResources().getColor(R.color.green));
                       correct++;}
                    else
                    if(button2.getText().toString().equals(correctop)) {
                        button2.setBackgroundColor(getResources().getColor(R.color.green));
                    correct++;}else
                    if(button3.getText().toString().equals(correctop)) {
                        button3.setBackgroundColor(getResources().getColor(R.color.green));
                    correct++;}else
                    if(button4.getText().toString().equals(correctop)) {
                        button4.setBackgroundColor(getResources().getColor(R.color.green));
                    correct++;}
                   if(fi!=0)
                       clicked=true;

               }}

        });
        next=findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sumbit_button=true;
                if(clicked){
                    clicked=false;
                    button1.setBackgroundColor(getResources().getColor(R.color.transparet));
                    button2.setBackgroundColor(getResources().getColor(R.color.transparet));
                    button3.setBackgroundColor(getResources().getColor(R.color.transparet));
                    button4.setBackgroundColor(getResources().getColor(R.color.transparet));
                     cursor.moveToNext();
                      cl++;
                     if(cl>5)
                     {
                         Intent intent=new Intent(MainActivity.this,End_page.class);
                         intent.putExtra("Total",correct);
                         startActivity(intent);
                     }
                     else {
                    questin.setText(cursor.getString(1));
                    button1.setText(cursor.getString(2));
                    button2.setText(cursor.getString(3));
                    button3.setText(cursor.getString(4));
                    button4.setText(cursor.getString(5));
                    correctop=cursor.getString(6);
                     }

                      if(que<5)
                    textView.setText("Question no:"+Integer.toString(++que));
                }
            }
        });
    }
    @Override
    public void onClick(View v) {
Button b1,b2;
       int id=v.getId();
        if(fi!=0 && sumbit_button){
            b2=findViewById(id);
            b2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            b1=findViewById(fi);
            b1.setBackgroundColor(getResources().getColor(R.color.transparet));
            fi=0;
            count=0;
        }
       if(id!=R.id.submit && count==0 && fi==0)
       {
           b1=findViewById(id);
           b1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
           fi=v.getId();
       }
    }

}