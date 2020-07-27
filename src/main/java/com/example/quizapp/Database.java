package com.example.quizapp;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    private  static final String DB_NAME="satrbuzz";
    private  static final   int DB_VERSION=2;

    public Database(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        updateDateBase(db,0,DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateDateBase(db,0,DB_VERSION);
    }
    private void insertData(SQLiteDatabase db,String quest,String optionA,String optionB,String optionC,String optionD,String result)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put("QUESTION",quest);
        contentValues.put("OPTION1",optionA);
        contentValues.put("OPTION2",optionB);
        contentValues.put("OPTION3",optionC);
        contentValues.put("OPTION4",optionD);
        contentValues.put("RESULT",result);

        db.insert("DRINK",null,contentValues);
    }
    private   void updateDateBase(SQLiteDatabase db,int old_Vesrion,int new_Version)
    {
        if(old_Vesrion<1)
        {
            db.execSQL("CREATE TABLE DRINK (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "QUESTION TEXT, "
                    + "OPTION1 TEXT, "
                    + "OPTION2 TEXT, "
                    + "OPTION3 TEXT, "
                    + "OPTION4 TEXT, "
                    + "RESULT TEXT);");
            insertData(db,"World largest country","Russia","Austrilia","U.K","America","Russia");
            insertData(db,"Which crop is sown on the largest area in India?","Rice","Wheat","SugarCane","Maize","Rice");
            insertData(db,"Entomology is the science that studies","Behaviour of human being","Insects","The origin and history of technical and scientific terms","The formation of rocks","The origin and history of technical and scientific terms");
            insertData(db,"Grand Central Terminal, Park Avenue, New York is the world's","highest railway station","longest railway station","largest railway station","None of the above","largest railway station");
            insertData(db,"Galileo was an astronomer who","developed the telescope","discovered four satellites of Jupiter"," discovered that the movement of pendulum produces a regular time measurement","All the above","discovered four satellites of Jupiter");
        }
        if(old_Vesrion<2)
            db.execSQL("alter table drink add column FAVOURITE NUMERIC");

    }
}
