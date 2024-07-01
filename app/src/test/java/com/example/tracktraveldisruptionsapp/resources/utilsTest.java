package com.example.tracktraveldisruptionsapp.resources;

import android.util.JsonReader;
import com.example.tracktraveldisruptionsapp.model.Station;
import com.example.tracktraveldisruptionsapp.model.StationList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import junit.framework.TestCase;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class utilsTest extends TestCase {

    public void testFileToString() {
        String str = utils.fileToString("src/main/java/com/example/tracktraveldisruptionsapp/resources/uk-train-stations.json");
        Gson gson = new GsonBuilder().setLenient().create();
        Type stationType = new TypeToken<List<Station>>() {}.getType();
        List<Station> stationList = gson.fromJson(str, stationType);
        for(Station s : stationList){
            System.out.println(s);
        }


    }
}