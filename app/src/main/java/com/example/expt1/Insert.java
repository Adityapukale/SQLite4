package com.example.expt1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Insert extends SQLiteOpenHelper {

    public static final String DbName="Student.db";
    public static final String TbName="StudentInfo";
    public static final String Name="Name";
    public static final String Pass="Pass";

       private String sqlquery= String.format("create table %s (%s text,%s Int) ", TbName,Name,Pass);



    public Insert(Context context) {
        super(context, DbName,null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(sqlquery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TbName);
        onCreate(db);

    }

    public boolean insertDate(String name, int pass){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Pass,pass);
        contentValues.put(Name,name);

        long result=db.insert(TbName,null,contentValues);

        if(result==-1)
            return false;
        else return true;

    }
    public Cursor Display(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cs= db.rawQuery("select * from "+TbName,null);
        return cs;
    }
}
