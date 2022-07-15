package com.myapplicationdev.android.id20042741.l09problemstatement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DBHelper db;
    TextView tvTest;
    Button btnShow, btnInsert;
    EditText etSongTitle, etSingerName, etYear;
    RadioGroup starsRG;
    RadioButton starRB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnShow = findViewById(R.id.btnShowList);
        btnInsert = findViewById(R.id.btnInsert);
        tvTest = findViewById(R.id.tvTest);
        etSongTitle = findViewById(R.id.etSongTitle);
        etSingerName = findViewById(R.id.etSingerName);
        etYear = findViewById(R.id.etYear);
        btnInsert = findViewById(R.id.btnInsert);
        starsRG = findViewById(R.id.rgStars);

        starsRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int selectedID = starsRG.getCheckedRadioButtonId();
                starRB = (RadioButton) findViewById(selectedID);
                Toast.makeText(MainActivity.this,
                        starRB.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String songTitle = etSongTitle.getText().toString();
                String singers = etSingerName.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());
                int stars = Integer.parseInt((String) starRB.getText());

                DBHelper db = new DBHelper(MainActivity.this);
                int result = db.insertData(songTitle, singers, year, stars);
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = new DBHelper(MainActivity.this);
               String show = db.getAllData();
               tvTest.setText(show);
            }
        });

    }
}