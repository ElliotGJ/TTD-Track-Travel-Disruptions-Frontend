package com.example.tracktraveldisruptionsapp.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tracktraveldisruptionsapp.NewJourneyActivity;
import com.example.tracktraveldisruptionsapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton button = findViewById(R.id.addbutton);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, NewJourneyActivity.class);
            startActivity(intent);
        });
    }
}
