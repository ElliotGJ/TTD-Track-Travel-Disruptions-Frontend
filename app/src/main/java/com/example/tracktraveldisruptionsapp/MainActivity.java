package com.example.tracktraveldisruptionsapp;

import android.content.Intent;
import android.content.res.Resources;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newJourneyClicked();

    }

public void newJourneyClicked(){
    Intent intent = new Intent(MainActivity.this, NewJourneyActivity.class);
//    intent.putExtra("ALBUM_KEY",albumList.get(position));
    startActivity(intent);
    }
}