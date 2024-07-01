package com.example.tracktraveldisruptionsapp.resources;

import android.util.JsonReader;
import com.example.tracktraveldisruptionsapp.model.Station;
import com.example.tracktraveldisruptionsapp.model.StationList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import junit.framework.TestCase;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class utilsTest extends TestCase {

    public void testFileToString() throws FileNotFoundException {
//        String str = utils.fileToString("src/main/java/com/example/tracktraveldisruptionsapp/resources/uk-train-stations.json");
//        System.out.println(str);
//        Gson gson = new GsonBuilder().setLenient().create();
//        Type stationType = new TypeToken<List<Station>>() {}.getType();
//        List<Station> stationList = gson.fromJson(str, stationType);
//        for(Station s : stationList){
//            System.out.println(s);
//        }

        BufferedReader br = new BufferedReader(new FileReader("src/main/java/com/example/tracktraveldisruptionsapp/resources/uk-train-stations.json"));
        Station[] station = new Gson().fromJson(br, Station[].class);
        for(Station s : station){
            System.out.println(s.getCsr());
        }


    }
}