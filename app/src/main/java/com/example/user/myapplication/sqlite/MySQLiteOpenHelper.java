package com.example.user.myapplication.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.user.myapplication.util.Constants;

/**
 * Created by kimmin-young on 2016. 3. 3..
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    public static final String TABLE = "product";
    public static final String NO = "no";
    public static final String URL = "url";
    public static final String LOC1 = "loc1";
    public static final String LOC2 = "loc2";
    public static final String POINT = "point";
    public static final String SDATE = "sdate";
    public static final String FDATE = "fdate";
    public static final String TITLE = "title";
    public static final String DESCR = "descr";
    public static final String DEPT = "dept";


    public MySQLiteOpenHelper(Context context) {
        super(context, "product", null, 1);
        Log.e(Constants.SQLITE, "generated");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "create table "+TABLE+
                "("+NO+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                URL+" TEXT," +
                LOC1+" TEXT," +
                LOC2+" TEXT," +
                POINT+" INTEGER," +
                SDATE+" TEXT," +
                FDATE+" TEXT," +
                TITLE+" TEXT," +
                DESCR+" TEXT," +
                DEPT+" TEXT);";

        Log.e(Constants.SQLITE,query);
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query="drop table if exists "+TABLE;

        Log.e(Constants.SQLITE,query);

        onCreate(db);

    }
}
