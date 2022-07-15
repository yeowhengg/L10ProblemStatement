package com.myapplicationdev.android.id20042741.l09problemstatement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.ArrayList;

public class ShowSongsActivity extends AppCompatActivity {
ListView lvShowSongs;
ArrayAdapter<Song> aaSong;
ArrayList<Song> alSong;
Spinner spinnerYear;
SpinnerAdapter saYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_songs);

        lvShowSongs = findViewById(R.id.lvSongs);
        DBHelper db = new DBHelper(ShowSongsActivity.this);

        alSong = new ArrayList<Song>();
        alSong = db.getAllData();
        aaSong = new ArrayAdapter<Song>(ShowSongsActivity.this, android.R.layout.simple_list_item_1, alSong);
        lvShowSongs.setAdapter(aaSong);

        lvShowSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ShowSongsActivity.this, ModifySongActivity.class);
                i.putExtra("obj", alSong.get(position));
                startActivity(i);
            }
        });
    }
}