package com.example.tracktraveldisruptionsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import com.example.tracktraveldisruptionsapp.databinding.ActivityNewJourneyBinding;

import java.util.ArrayList;

public class NewJourneyActivity extends AppCompatActivity {

    // Initialize variable
    TextView textview;
    ArrayList<String> arrayList;
    Dialog dialog;
    MainActivity mainActivity;
    ActivityNewJourneyBinding binding;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_station_selection);


        // assign variable
        textview=findViewById(R.id.from_input);

        // initialize array list
        arrayList=new ArrayList<>();

        // set value in array list
        arrayList.add("DSA Self Paced");
        arrayList.add("Complete Interview Prep");
        arrayList.add("Amazon SDE Test Series");
        arrayList.add("Compiler Design");
        arrayList.add("Git & Github");
        arrayList.add("Python foundation");
        arrayList.add("Operating systems");
        arrayList.add("Theory of Computation");

        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Initialize dialog
                dialog=new Dialog(NewJourneyActivity.this);

                // set custom dialog
                dialog.setContentView(R.layout.dialog_searchable_spinner);

                // set custom height and width
                dialog.getWindow().setLayout(650,800);

                // set transparent background
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                // show dialog
                dialog.show();

                // Initialize and assign variable
                EditText editText=dialog.findViewById(R.id.edit_text);
                ListView listView=dialog.findViewById(R.id.list_view);

                // Initialize array adapter
                ArrayAdapter<String> adapter=new ArrayAdapter<>(NewJourneyActivity.this, android.R.layout.simple_list_item_1,arrayList);

                // set adapter
                listView.setAdapter(adapter);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        adapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // when item selected from list
                        // set selected item on textView
                        textview.setText(adapter.getItem(position));

                        // Dismiss dialog
                        dialog.dismiss();
                    }
                });
            }
        });
    }
}
