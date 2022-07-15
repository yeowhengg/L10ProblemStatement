package com.myapplicationdev.android.id20042741.l09problemstatement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ModifySongActivity extends AppCompatActivity {
    TextView tvTest;
    EditText etSongID, etTitle, etSinger, etYear;
    Button btnUpdate, btnDelete, btnCancel;
    RadioGroup rgStars;
    RadioButton rbStars, rbBtn1, rbBtn2, rbBtn3, rbBtn4, rbBtn5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_song);

        tvTest = findViewById(R.id.tvTest);
        rgStars = findViewById(R.id.rgStars2);
        etSongID = findViewById(R.id.etSongID);
        etTitle = findViewById(R.id.etSongTitle);
        etSinger = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear2);
        rbBtn1 = findViewById(R.id.rbBtn1);
        rbBtn2 = findViewById(R.id.rbBtn2);
        rbBtn3 = findViewById(R.id.rbBtn3);
        rbBtn4 = findViewById(R.id.rbBtn4);
        rbBtn5 = findViewById(R.id.rbBtn5);
        btnUpdate = findViewById(R.id.btnUpdate2);
        btnDelete = findViewById(R.id.btnDelete2);
        btnCancel = findViewById(R.id.btnCancel);

        Intent getObj = getIntent();
        Song data = (Song) getObj.getSerializableExtra("obj");

        Intent i = new Intent(ModifySongActivity.this, ShowSongsActivity.class);

        etSongID.setText(String.format("%d",data.get_id()));
        etTitle.setText(data.getTitle());
        etSinger.setText(data.getSinger());
        etYear.setText(String.format("%d", data.getYear()));

        if(data.getStars() == 1){
            rbBtn1.setChecked(true);
        }else if(data.getStars() == 2){
            rbBtn2.setChecked(true);
        }else if(data.getStars() == 3){
            rbBtn3.setChecked(true);
        }else if(data.getStars() == 4){
            rbBtn4.setChecked(true);
        }else if(data.getStars() == 5){
            rbBtn5.setChecked(true);
        }

        int selectedID = rgStars.getCheckedRadioButtonId();
        rbStars = (RadioButton) findViewById(selectedID);

        rgStars.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int selectedID = rgStars.getCheckedRadioButtonId();
                rbStars = (RadioButton) findViewById(selectedID);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = data.get_id();
                String title = etTitle.getText().toString();
                String singer = etSinger.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());
                int stars = Integer.parseInt(rbStars.getText().toString());
                Song song = new Song(id, title, singer, year, stars);

                DBHelper db = new DBHelper (ModifySongActivity.this);
                long result = db.updateData(song);

                if(result != -1){
                    i.putExtra("msg", "Successfully updated");
                    startActivity(i);
                    finish();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(ModifySongActivity.this);
                int indexToDelete = data.get_id();
                long result = db.deleteData(indexToDelete);

                if(result != -1){
                    i.putExtra("msg", "Successfully deleted!");
                    startActivity(i);
                    finish();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.putExtra("msg", "cancelled");
                startActivity(i);
                finish();
            }
        });



    }
}