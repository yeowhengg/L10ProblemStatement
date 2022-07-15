package com.myapplicationdev.android.id20042741.l09problemstatement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.RegexValidator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

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
        int selectedID = starsRG.getCheckedRadioButtonId();
        starRB = (RadioButton) findViewById(selectedID);

        starsRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int selectedID = starsRG.getCheckedRadioButtonId();
                Toast.makeText(MainActivity.this, String.format("%d", selectedID), Toast.LENGTH_SHORT).show();
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
                int stars = Integer.parseInt(starRB.getText().toString());
                int year = 0;
                String pattern = "(19|20)(\\d){2}";
                Boolean regex = Pattern.matches(pattern, etYear.getText().toString());

                if(regex) { year = Integer.parseInt(etYear.getText().toString()); } else {Toast.makeText(MainActivity.this, "Ensure year starts from 19 or 20 and followed by 2 digits!", Toast.LENGTH_SHORT).show();}

                if(songTitle.isEmpty() || singers.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please fill up the details!", Toast.LENGTH_SHORT).show();
                }else{
                    DBHelper db = new DBHelper(MainActivity.this);
                    long result = db.insertData(songTitle, singers, year, stars);

                    if(result != -1){
                        Toast.makeText(MainActivity.this, "Successfully inserted a new record!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = new DBHelper(MainActivity.this);
                Intent i = new Intent(MainActivity.this, ShowSongsActivity.class);
                startActivity(i);

               //String show = db.getAllData();
               //tvTest.setText(show);
            }
        });

    }
}