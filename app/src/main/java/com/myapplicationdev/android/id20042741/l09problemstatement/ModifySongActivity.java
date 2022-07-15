package com.myapplicationdev.android.id20042741.l09problemstatement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ModifySongActivity extends AppCompatActivity {
    TextView tvTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_song);
        tvTest = findViewById(R.id.tvTest);

        Intent i = getIntent();
        Song data = (Song) i.getSerializableExtra("obj");
        tvTest.setText(data.getSinger());

    }
}