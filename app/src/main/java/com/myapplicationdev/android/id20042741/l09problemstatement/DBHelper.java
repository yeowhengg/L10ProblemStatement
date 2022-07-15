package com.myapplicationdev.android.id20042741.l09problemstatement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ndpsongs.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_SONG = "song";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_SINGER = "singer";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_STARS = "stars";

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createNDPSongTableSQL = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY " +
                "AUTOINCREMENT, %s TEXT, %s TEXT, %s INTEGER, %s INTEGER ) ", TABLE_SONG,
                COLUMN_ID,  COLUMN_TITLE, COLUMN_SINGER, COLUMN_YEAR, COLUMN_STARS);

        db.execSQL(createNDPSongTableSQL);
        Log.i("info", "created table");

        //dummy record
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, "Oh No");
        values.put(COLUMN_SINGER, "Oli Sykes");
        values.put(COLUMN_YEAR, "2014");
        values.put(COLUMN_STARS, "5");

        db.insert(TABLE_SONG, null, values);

        Log.i("info", "dummy created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONG);
        onCreate(db);
    }

    public ArrayList<Song> getAllData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] column = {COLUMN_TITLE, COLUMN_SINGER, COLUMN_YEAR, COLUMN_STARS};
        Cursor cursor = db.query(TABLE_SONG, column, null, null, null, null, null, null);
        ArrayList<Song> alSong = new ArrayList<Song>();
        if(cursor.moveToFirst()){
            do{
                String title = cursor.getString(0);
                String singer = cursor.getString(1);
                int year = cursor.getInt(2);
                int stars = cursor.getInt(3);
                Song song = new Song(title, singer, year, stars);
                alSong.add(song);
            }while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return alSong;
    }

    public long insertData(String title, String singer, int year, int stars){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_SINGER, singer);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_STARS, stars);
        long result = db.insert(TABLE_SONG, null, values);
        return result;
    }
}
