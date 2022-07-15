package com.myapplicationdev.android.id20042741.l09problemstatement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ShowSongsActivity extends AppCompatActivity {
ListView lvShowSongs;
ArrayAdapter<Song> aaSong;
ArrayList<Song> alSong;
Button btnFilter;
Spinner spinnerYear;
ArrayAdapter<String> saYear;
Boolean filter = false;
String[] allYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_songs);

        lvShowSongs = findViewById(R.id.lvSongs);
        btnFilter = findViewById(R.id.btnShow5Star);

        spinnerYear = findViewById(R.id.spinnerID);

        DBHelper db = new DBHelper(ShowSongsActivity.this);
        alSong = new ArrayList<Song>();
        alSong = db.get5stars(filter);
        aaSong = new ArrayAdapter<Song>(ShowSongsActivity.this, android.R.layout.simple_list_item_1, alSong);
        lvShowSongs.setAdapter(aaSong);

        spinnerSetYear();

        lvShowSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ShowSongsActivity.this, ModifySongActivity.class);
                i.putExtra("obj", alSong.get(position));
                startActivity(i);
            }
        });

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (filter) {
                    btnFilter.setText("SHOW ALL SONG");
                } else {
                    btnFilter.setText("SHOW ALL SONG WITH 5 STAR");
                }

                DBHelper db = new DBHelper(ShowSongsActivity.this);
                alSong = db.get5stars(filter);
                aaSong = new ArrayAdapter<Song>(ShowSongsActivity.this, android.R.layout.simple_list_item_1, alSong);
                lvShowSongs.setAdapter(aaSong);
                aaSong.notifyDataSetChanged();
                if(alSong.isEmpty()) Toast.makeText(ShowSongsActivity.this, "Nothing to display", Toast.LENGTH_SHORT).show();


                filter = !filter;
            }
        });

        spinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DBHelper db = new DBHelper(ShowSongsActivity.this);
                alSong = db.filterByYear((String) parent.getItemAtPosition(position));
                aaSong = new ArrayAdapter<Song>(ShowSongsActivity.this, android.R.layout.simple_list_item_1, alSong);
                lvShowSongs.setAdapter(aaSong);
                aaSong.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void spinnerSetYear(){
        allYear = new String[alSong.size()];
        for(int i = 0; i < allYear.length; i++){
            allYear[i] = String.format("%d",alSong.get(i).getYear());
        }
        saYear = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allYear);
        spinnerYear.setAdapter(saYear);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle retrMsg = getIntent().getExtras();
        String msg = "";
        if(retrMsg != null){
            msg = retrMsg.getString("msg", "-");
        }
        Toast.makeText(ShowSongsActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

}