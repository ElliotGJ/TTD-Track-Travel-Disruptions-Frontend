package com.example.tracktraveldisruptionsapp.ui.main;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.LayoutInflater;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tracktraveldisruptionsapp.databinding.ActivityMainBinding;
import com.example.tracktraveldisruptionsapp.model.BackendMap;
import com.example.tracktraveldisruptionsapp.model.Journey;
import com.example.tracktraveldisruptionsapp.model.RailDataDTO;
import com.example.tracktraveldisruptionsapp.resources.ItemSpaceDecorator;
import com.example.tracktraveldisruptionsapp.ui.addjourney.NewJourneyActivity;
import com.example.tracktraveldisruptionsapp.R;
import com.example.tracktraveldisruptionsapp.ui.disruptions.DisruptionsActivity;
import com.example.tracktraveldisruptionsapp.ui.editjourney.EditJourneyActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<BackendMap> journeys;
    private JourneyAdapter journeyAdapter;
    private MainActivityViewModel viewModel;
    private ActivityMainBinding binding;
    public static final String KEY = "journey_raildata";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        FloatingActionButton button = findViewById(R.id.addbutton);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, NewJourneyActivity.class);
            startActivity(intent);
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Train Platform Updates";
            String description = "Notifications for train platform changes";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("1", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1);
            }
        }

        FloatingActionButton refreshButton = findViewById(R.id.refreshButton);
        refreshButton.setOnClickListener(view -> {
            getAllJourneys();
            Toast.makeText(this, "Refreshed", Toast.LENGTH_SHORT).show();
        });
        //Refactor the displayInRecyclerView method into here
        recyclerView = binding.recyclerView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        ItemSpaceDecorator decorator = new ItemSpaceDecorator(40);
        recyclerView.addItemDecoration(decorator);

        journeyAdapter = new JourneyAdapter(new ArrayList<>(), this, view -> {
            BackendMap backendMap = (BackendMap) view.getTag();
            Journey journey = backendMap.getJourneyDTO();
            Intent intent = new Intent(MainActivity.this, EditJourneyActivity.class);
            intent.putExtra("JOURNEY_OBJECT", journey);
            startActivity(intent);
        });

        //Prevent repeat added space between items
        recyclerView.setAdapter(journeyAdapter);
        journeyAdapter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(int position, RailDataDTO railData) {
                Intent intent = new Intent(MainActivity.this, DisruptionsActivity.class);
                intent.putExtra(KEY, railData);
                startActivity(intent);
            }
        });
        getAllJourneys();

    }

    public void sendNotification(String title, String content) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            NotificationChannel channel = new NotificationChannel("CHANNEL_ID", "Channel Name", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "CHANNEL_ID")
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManagerCompat.notify(new Random().nextInt(), builder.build());
    }

    private void getAllJourneys(){
        viewModel.getRepositoryLiveData().observe(this, journeyList -> {

            journeys = (ArrayList<BackendMap>) journeyList;
//            System.out.println("journeys =" + journeys);
            if (journeys == null || journeys.isEmpty()){
                showAddJourneyMessage(true);
            }else {
                showAddJourneyMessage(false);
            }

           journeyAdapter.updateJourneys(journeys);
            sendNotification("Train Platform Updates", "Train platform has changed");
        });

    }


    private void displayInRecyclerView(){
        recyclerView = binding.recyclerView;
        journeyAdapter = new JourneyAdapter(journeys,this,view->{
            BackendMap backendMap = (BackendMap) view.getTag();
            Journey journey = backendMap.getJourneyDTO();
            Intent intent = new Intent(MainActivity.this, EditJourneyActivity.class);
            System.out.println("JourneysObj "+ journey);

//            intent.putExtra("journeyID", journey.getJourneyID());

            startActivity(intent);
        });

        recyclerView.setAdapter(journeyAdapter);
        journeyAdapter.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(int position, RailDataDTO railData) {
                Intent intent = new Intent(MainActivity.this,DisruptionsActivity.class);
                intent.putExtra(KEY, railData);
                startActivity(intent);
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        ItemSpaceDecorator decorator = new ItemSpaceDecorator(40);
        recyclerView.addItemDecoration(decorator);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        journeyAdapter.notifyDataSetChanged();

    }


    private void showAddJourneyMessage(boolean visible){
        if(visible) {
            binding.noJourneyImg.setVisibility(View.VISIBLE);
            binding.noJourneyMsg.setVisibility(View.VISIBLE);
        }else{
            binding.noJourneyImg.setVisibility(View.INVISIBLE);
            binding.noJourneyMsg.setVisibility(View.INVISIBLE);
        }

    }

}
