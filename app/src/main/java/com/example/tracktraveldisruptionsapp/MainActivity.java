package com.example.tracktraveldisruptionsapp;

import android.content.Intent;
import android.content.res.Resources;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, NewJourneyActivity.class);
            startActivity(intent);
        });


    }

    public void newJourneyClicked(View view){

    }


}