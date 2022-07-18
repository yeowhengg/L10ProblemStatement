package com.myapplicationdev.android.id20042741.l09problemstatement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.lang.reflect.Array;
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

    public long updateData(Song updateData){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, updateData.getTitle());
        values.put(COLUMN_SINGER, updateData.getSinger());
        values.put(COLUMN_YEAR, updateData.getYear());
        values.put(COLUMN_STARS, updateData.getStars());

        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(updateData.get_id())};
        long result = db.update(TABLE_SONG, values, condition, args);
        db.close();
        return result;
    }

    public long deleteData (int indexToDelete){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(indexToDelete)};
        long result = db.delete(TABLE_SONG, condition, args);
        db.close();
        return result;
    }

    public ArrayList<Song> get5stars(Boolean filter){
        ArrayList<Song> songal = new ArrayList<Song>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGER, COLUMN_YEAR, COLUMN_STARS};
        String conditions = filter ? COLUMN_STARS + "= ?" : null;
        String[] args = filter ? new String[]{"5"} : null;
        Cursor cursor = db.query(TABLE_SONG, columns, conditions, args, null, null, null, null);

        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singer = cursor.getString(2);
                int year = cursor.getInt(3);
                int stars = cursor.getInt(4);
                Song song = new Song(id, title, singer, year, stars);
                songal.add(song);

                }while(cursor.moveToNext());

                cursor.close();
                db.close();
            }
        return songal;
        }

     public ArrayList<Song> filterByYear (String filteredYear){

        if(filteredYear.equalsIgnoreCase("ALL YEAR")){
            return get5stars(false);
        }else{
            ArrayList<Song> alFilteredSong = new ArrayList<Song>();
            SQLiteDatabase db = this.getReadableDatabase();
            String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGER, COLUMN_YEAR, COLUMN_STARS};
            String conditions = COLUMN_YEAR + "= ?";
            String[] args = {filteredYear};
            Cursor cursor = db.query(TABLE_SONG, columns, conditions, args, null, null, null, null);

            if(cursor.moveToFirst()){
                do{
                    int id = cursor.getInt(0);
                    String title = cursor.getString(1);
                    String singer = cursor.getString(2);
                    int year = cursor.getInt(3);
                    int stars = cursor.getInt(4);

                    Song song = new Song(id, title, singer, year, stars);
                    alFilteredSong.add(song);

                }while(cursor.moveToNext());
            }

            cursor.close();
            db.close();
            return alFilteredSong;
        }

     }

     public ArrayList<String> returnDistinctYear (){

        ArrayList<String> alDisYear = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"year"};
        String sql = "SELECT DISTINCT year FROM song";
        Cursor cursor = db.query(true, TABLE_SONG, columns, null, null, null ,null, null, null);

        if(cursor.moveToFirst()){
            do{
                int year = cursor.getInt(0);
                alDisYear.add(String.valueOf(year));
            }while(cursor.moveToNext());
        }

         return alDisYear;
     }
}
