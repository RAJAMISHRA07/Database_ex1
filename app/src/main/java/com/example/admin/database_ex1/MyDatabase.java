package com.example.admin.database_ex1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Admin on 12/28/2016.
 */
//cretae a separate java file (controller)
public class MyDatabase {

    //5= declare required variables
    MyHelper myHelper;
    SQLiteDatabase sqLiteDatabase;//for doing DML operations (insert,update,delete)

    // 6=create object for my helper variable
    public MyDatabase(Context c) {
        myHelper = new MyHelper(c, "techpalle.db", null, 1);
    }

    //7 create s qlite database object using open method
    public void open() {
        sqLiteDatabase = myHelper.getWritableDatabase();
    }

    //8=perform DML operations
    public void insertStudent(String name, String course) {
        ContentValues cv = new ContentValues();
        cv.put("sname", name);
        cv.put("scourse", course);
        //table name,null,content values
        sqLiteDatabase.insert("student", null, cv);

    }

    //9= keep update and delete ()on hlod
    public Cursor queryStudent() {
        Cursor c = null;

        //q1=read all student details
       // c = sqLiteDatabase.query("student", null, null, null, null, null, null);

        //14==q2==it will take only _id=2 row
       // c=sqLiteDatabase.query("student",null,"_id=2",null,null,null,null,null);

        //q3=read only details of student"debiprasad"
        c=sqLiteDatabase.query("student",null,"sname='DEBIPRASAD'",null,null,null,null,null);

        //q4=read only students whose name starts with M
       // c=sqLiteDatabase.query("student",null,"sname LIKE 'm%'",null,null,null,null,null);
        return c;
    }

    //step10=close data base
    public void close() {
        sqLiteDatabase.close();
    }


    //3= create inner helper class for DDL operation
    private class MyHelper extends SQLiteOpenHelper {

        public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            //step 4 cretae all table
            db.execSQL("create table student(_id integer primary key,sname text,scourse text); ");
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}





